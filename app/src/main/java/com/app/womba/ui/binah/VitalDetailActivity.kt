package com.app.womba.ui.binah

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.StrictMode
import android.provider.MediaStore
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.womba.BR
import com.app.womba.R
import com.app.womba.base.BaseActivity
import com.app.womba.databinding.ActivityVitalDetailBinding
import com.app.womba.model.fullreport.HealthModel
import com.app.womba.model.fullreport.Sdrr
import com.app.womba.ui.landing.LandingActivity
import com.app.womba.utils.*
import com.app.womba.utils.customViews.LoadingDialog
import com.evitalapp.model.fullreport.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.gson.Gson
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.io.ByteArrayOutputStream

class VitalDetailActivity : BaseActivity<ActivityVitalDetailBinding, VitalDetailVM>(),
    View.OnClickListener {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_vital_detail

    override val viewModel: VitalDetailVM
        get() = ViewModelProviders.of(this).get(VitalDetailVM::class.java)

    override val context: Context
        get() = this

    var behavior: BottomSheetBehavior<*>? = null

    override fun onCreate() {
        setSupportActionBar(mViewDataBinding!!.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        behavior = BottomSheetBehavior.from(mViewDataBinding!!.bottomSheet)

        val builder: StrictMode.VmPolicy.Builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        intent?.getStringExtra(InterConsts.HEALTH_REPORT)?.let {
            val data = Gson().fromJson(it, HealthModel::class.java)
            initView(data)
        }
    }

    private fun initView(data: HealthModel?) {
        data?.getReport()?.getHr()?.let {
            initHR(it)
        }
        data?.getReport()?.getRespiration()?.let {
            initRespiration(it)
        }
        data?.getReport()?.getBloodPressure()?.let {
            initBP(it)
        }

        data?.getReport()?.getOxygen()?.let {
            initOxygen(it)
        }

        data?.getReport()?.getStress()?.let {
            initStress(it)
        }

        data?.getScanReport()?.TimeParameters?.getSdrr()?.let {
            initHRV(it)
        }

        data?.getFullData()?.getTimeParameters()?.getSdrr()?.let {
            initHRV(it)
        }
    }

    private fun initHRV(it: Sdrr) {
        it?.getValue()?.let {
            mViewDataBinding!!.hrvSdnn.text = String.format("%.2f", it)
        }
    }

    private fun initStress(it: Stress) {
        it?.getValue()?.let {
            showStressLevel(it)
        }
    }

    private fun initOxygen(it: Oxygen) {
        it?.getSaturationLevel()?.getValue()?.let {
            mViewDataBinding!!.breathValue.text = it.toString()
        }
    }

    private fun showStressLevel(target: Int) = when (target) {
        in 0..80 -> {
            mViewDataBinding!!.stressLevel.text = getString(R.string.low)
            mViewDataBinding!!.stressValue.setImageResource(R.drawable.ic_happy)
        }
        in 80..150 -> {
            mViewDataBinding!!.stressLevel.text = getString(R.string.normal)
            mViewDataBinding!!.stressValue.setImageResource(R.drawable.ic_smile_yellow)
        }
        in 150..400 -> {
            mViewDataBinding!!.stressLevel.text = getString(R.string.mild)
            mViewDataBinding!!.stressValue.setImageResource(R.drawable.ic_sceptic)
        }
        in 400..800 -> {
            mViewDataBinding!!.stressLevel.text = getString(R.string.high)
            mViewDataBinding!!.stressValue.setImageResource(R.drawable.ic_sad)
        }
        else -> {
            mViewDataBinding!!.stressLevel.text = getString(R.string.extremely_high)
            mViewDataBinding!!.stressValue.setImageResource(R.drawable.ic_very_sad)
        }

    }

    private fun initBP(it: BloodPressure) {
        it?.getDiastolic()?.let { text ->
            mViewDataBinding!!.bpValue.text = "$text/${it.getSystolic()}"
        }
    }

    private fun initRespiration(data: Respiration) {
        data?.getValue()?.let {
            mViewDataBinding!!.Spo2Value.text = it.toString()
        }
    }

    private fun initHR(data: Hr) {
        data?.getMeanPulseRate()?.getValue()?.let {
            mViewDataBinding!!.bpmValue.text = it.toString()
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun initListeners() {
        mViewDataBinding!!.heartShare.setOnClickListener(this)
        mViewDataBinding!!.oxygenShare.setOnClickListener(this)
        mViewDataBinding!!.respirationShare.setOnClickListener(this)
        mViewDataBinding!!.stressShare.setOnClickListener(this)
        mViewDataBinding!!.hrvShare.setOnClickListener(this)
        mViewDataBinding!!.bloodShare.setOnClickListener(this)
        mViewDataBinding!!.fullReport.setOnClickListener(this)
        mViewDataBinding!!.saveBtn.setOnClickListener(this)
        mViewDataBinding!!.heartInfo.setOnClickListener(this)
        mViewDataBinding!!.oxygenInfo.setOnClickListener(this)
        mViewDataBinding!!.respirationInfo.setOnClickListener(this)
        mViewDataBinding!!.stressInfo.setOnClickListener(this)
        mViewDataBinding!!.hrvInfo.setOnClickListener(this)
        mViewDataBinding!!.bloodInfo.setOnClickListener(this)
        mViewDataBinding!!.downArrow.setOnClickListener(this)

        mViewModel!!.getLoader().observe(this,
            Observer { mData ->
                if (mData) {
                    LoadingDialog.getLoader().showLoader(this)
                } else {
                    LoadingDialog.getLoader().dismissLoader()
                }
            })

        mViewModel!!.getResponse().observe(this,
            Observer { mData ->
                if (mData != null && responseHandler(mData.code, mData.message)) {
                    navigateClass<LandingActivity>()
                    finishAffinity()
                }
            })


    }

    override fun onClick(v: View?) {
        hideKeyboard()
        when (v?.id) {
            R.id.fullReport -> {
                exportResults(mViewDataBinding!!.parentLayout)
            }

            R.id.saveBtn -> {
                intent?.getStringExtra(InterConsts.HEALTH_REPORT)?.let {
//                    val data = it.replace("\"", "'")
                    val data = Gson().fromJson(it, HealthModel::class.java)
                    data.setPatientId(getUserData().data!!.get_id())
                    viewModel.saveEvitalsData(
                       data
                    )
                }

            }
            R.id.heartShare -> {
                exportResults(mViewDataBinding!!.heartLayout)
            }
            R.id.oxygenShare -> {
                exportResults(mViewDataBinding!!.oxygenLayout)
            }
            R.id.respirationShare -> {
                exportResults(mViewDataBinding!!.respirationLayout)
            }
            R.id.stressShare -> {
                exportResults(mViewDataBinding!!.stressLayout)
            }
            R.id.hrvShare -> {
                exportResults(mViewDataBinding!!.hrvLayout)
            }
            R.id.bloodShare -> {
                exportResults(mViewDataBinding!!.bloodLayout)
            }
            R.id.downArrow -> {
                behavior?.state = BottomSheetBehavior.STATE_COLLAPSED
            }
            R.id.heartInfo -> {
                behavior?.state = BottomSheetBehavior.STATE_COLLAPSED
                mViewDataBinding!!.respirationTable.visibility = View.GONE
                mViewDataBinding!!.oxygenTable.visibility = View.GONE
                mViewDataBinding!!.stressTable.visibility = View.GONE
                setInfo(getString(R.string.heart_rate), getString(R.string.heart_info))
            }
            R.id.oxygenInfo -> {
                behavior?.state = BottomSheetBehavior.STATE_COLLAPSED
                mViewDataBinding!!.respirationTable.visibility = View.GONE
                mViewDataBinding!!.oxygenTable.visibility = View.VISIBLE
                mViewDataBinding!!.stressTable.visibility = View.GONE
                setInfo(getString(R.string.oxygen_saturation), getString(R.string.oxygen_info))
            }
            R.id.respirationInfo -> {
                behavior?.state = BottomSheetBehavior.STATE_COLLAPSED
                mViewDataBinding!!.respirationTable.visibility = View.VISIBLE
                mViewDataBinding!!.oxygenTable.visibility = View.GONE
                mViewDataBinding!!.stressTable.visibility = View.GONE
                setInfo(getString(R.string.respiration), getString(R.string.respiration_info))
            }
            R.id.stressInfo -> {
                behavior?.state = BottomSheetBehavior.STATE_COLLAPSED
                mViewDataBinding!!.respirationTable.visibility = View.GONE
                mViewDataBinding!!.oxygenTable.visibility = View.GONE
                mViewDataBinding!!.stressTable.visibility = View.VISIBLE
                setInfo(getString(R.string.stress), getString(R.string.stress_info))
            }
            R.id.hrvInfo -> {
                behavior?.state = BottomSheetBehavior.STATE_COLLAPSED
                mViewDataBinding!!.respirationTable.visibility = View.GONE
                mViewDataBinding!!.oxygenTable.visibility = View.GONE
                mViewDataBinding!!.stressTable.visibility = View.GONE
                setInfo(getString(R.string.hrv_sdnn), getString(R.string.hrv_info))
            }
            R.id.bloodInfo -> {
                behavior?.state = BottomSheetBehavior.STATE_COLLAPSED
                mViewDataBinding!!.respirationTable.visibility = View.GONE
                mViewDataBinding!!.oxygenTable.visibility = View.GONE
                mViewDataBinding!!.stressTable.visibility = View.GONE
                setInfo(getString(R.string.blood_pressure), getString(R.string.blood_info))
            }
        }
    }

    private fun setInfo(title: String, info: String) {
        behavior?.state = BottomSheetBehavior.STATE_EXPANDED
        mViewDataBinding!!.titleTxt.text = title
        mViewDataBinding!!.infoTxt.text = info
    }

    private fun exportResults(parentLayout: View) {
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        val bitmap: Bitmap = Screenshot.takeScreenShot(parentLayout)
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        shareIntent.type = "image/jpg"
                        saveTempBitmap(bitmap)?.let {
                            shareIntent.putExtra(Intent.EXTRA_STREAM, saveTempBitmap(bitmap))
                        } ?: kotlin.run {
                            shareIntent.putExtra(
                                Intent.EXTRA_STREAM,
                                getImageUriFromBitmap(this@VitalDetailActivity, bitmap)
                            )
                        }
                        startActivity(Intent.createChooser(shareIntent, "Full Report"))
                    } else {
                        hideKeyboard()
                        showToast(
                            "Can't share report"
                        )
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()
    }

    private fun getImageUriFromBitmap(context: Context, bitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path =
            MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "Title", null)
        return Uri.parse(path.toString())
    }

}
