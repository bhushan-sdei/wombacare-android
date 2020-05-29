package com.app.womba.ui.appointments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.afollestad.assent.Permission
import com.afollestad.assent.askForPermissions
import com.app.womba.BR
import com.app.womba.R
import com.app.womba.VideoCallActivity
import com.app.womba.base.BaseActivity
import com.app.womba.databinding.ActivityAppointmentDetailBinding
import com.app.womba.model.AppointmentModel
import com.app.womba.network.WS_BASE_DOCTOR_IMAGE_URL
import com.app.womba.ui.binah.VitalScanActivity
import com.app.womba.ui.videoCalling.VideoCallingActivity
import com.app.womba.utils.*
import kotlinx.android.synthetic.main.activity_appointment_detail.*
import kotlinx.android.synthetic.main.activity_appointment_detail.imageView_doctor_profile
import kotlinx.android.synthetic.main.activity_appointment_detail.txtVitalScan

class AppointmentDetailActivity :
    BaseActivity<ActivityAppointmentDetailBinding, AppointmentDetailsVM>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_appointment_detail

    override val viewModel: AppointmentDetailsVM
        get() = ViewModelProviders.of(this).get(AppointmentDetailsVM::class.java)

    override val context: Context
        get() = this

    override fun onCreate() {
        setData()
    }

    override fun initListeners() {
        imgBack.setOnClickListener {
            finish()
        }

        txtViewErx.setOnClickListener {
            val mData =
                intent.getParcelableExtra<AppointmentModel.DataBean.PatientconsultedformsDataBean>(
                    InterConsts.PRESCRIPTION
                )
            val intent = Intent(context, PrescriptionActivity::class.java)
            intent.putExtra(InterConsts.PRESCRIPTION, mData)
            startActivity(intent)
        }
        txtVitalScan.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 23) {
                askForPermissions(
                    Permission.WRITE_EXTERNAL_STORAGE,
                    Permission.RECORD_AUDIO,
                    Permission.ACCESS_COARSE_LOCATION,
                    Permission.ACCESS_FINE_LOCATION,
                    Permission.READ_EXTERNAL_STORAGE,
                    Permission.CAMERA
                ) {
                    if (it.isAllDenied(
                            Permission.WRITE_EXTERNAL_STORAGE,
                            Permission.RECORD_AUDIO,
                            Permission.ACCESS_COARSE_LOCATION,
                            Permission.ACCESS_FINE_LOCATION,
                            Permission.READ_EXTERNAL_STORAGE,
                            Permission.CAMERA
                        )
                    ) {
                        showSnackBarPermission(txtVitalScan, this)
                    } else {

                        navigateClass<VitalScanActivity>()
                    }
                }
            } else {
                navigateClass<VitalScanActivity>()
            }
        }

        txtVideoCall.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 23) {
                askForPermissions(
                    Permission.WRITE_EXTERNAL_STORAGE,
                    Permission.RECORD_AUDIO,
                    Permission.ACCESS_COARSE_LOCATION,
                    Permission.ACCESS_FINE_LOCATION,
                    Permission.READ_EXTERNAL_STORAGE,
                    Permission.CAMERA
                ) {
                    if (it.isAllDenied(
                            Permission.WRITE_EXTERNAL_STORAGE,
                            Permission.RECORD_AUDIO,
                            Permission.ACCESS_COARSE_LOCATION,
                            Permission.ACCESS_FINE_LOCATION,
                            Permission.READ_EXTERNAL_STORAGE,
                            Permission.CAMERA
                        )
                    ) {
                        showSnackBarPermission(imgBack, this)
                    } else {

                        val mData =
                            intent.getParcelableExtra<AppointmentModel.DataBean>(InterConsts.EXTRA)
                        val intent = Intent(context, VideoCallingActivity::class.java)
                        intent.putExtra(InterConsts.EXTRA, mData)
                        intent.putExtra(
                            InterConsts.DOCTOR_INFO,
                            intent.getParcelableExtra<AppointmentModel.DataBean.DoctorInfoBean>(
                                InterConsts.DOCTOR_INFO
                            )
                        )
                        startActivity(intent)
                    }
                }
            } else {

                val mData = intent.getParcelableExtra<AppointmentModel.DataBean>(InterConsts.EXTRA)
                val intent = Intent(context, VideoCallingActivity::class.java)
                intent.putExtra(InterConsts.EXTRA, mData)
                intent.putExtra("DOC_NAME", mData!!.doctorInfo!!.firstname+" "+mData!!.doctorInfo!!.lastname)
                intent.putExtra(
                    InterConsts.DOCTOR_INFO,
                    intent.getParcelableExtra<AppointmentModel.DataBean.DoctorInfoBean>(InterConsts.DOCTOR_INFO)
                )
                startActivity(intent)

            }


        }

    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        val mData = intent.getParcelableExtra<AppointmentModel.DataBean>(InterConsts.EXTRA)
        val mDataDocInfo =
            intent.getParcelableExtra<AppointmentModel.DataBean.DoctorInfoBean>(InterConsts.DOCTOR_INFO)

        textView_name.text = "Dr." + mDataDocInfo!!.firstname + " " + mDataDocInfo.lastname
        txtAddress.text =
            mDataDocInfo.address + "," + mDataDocInfo.city + "," + mDataDocInfo.statename + "," + mDataDocInfo.country
        txtDate.text =
            mData!!.weekDay + ", " + convertUTCToLocalDate(mData.appointmentDate) + "\n" +
                    getTime(mData.finalAppointmentStartwithClincTimezone) + " - " + getTime(mData.finalAppointmentEndwithClincTimezone)

        if (intent.hasExtra(InterConsts.FROM)) {
            if (intent.hasExtra(InterConsts.PRESCRIPTION)) {
                txtViewErx.visibility = View.VISIBLE
                txtVideoCall.visibility = View.GONE
            } else {
                txtVideoCall.visibility = View.VISIBLE
                txtViewErx.visibility = View.GONE
            }
        } else {
            txtViewErx.visibility = View.GONE
            txtVitalScan.visibility = View.VISIBLE
            txtVideoCall.visibility = View.VISIBLE
        }

//        var appointtype = ""
//
//        for (model in mDataDocInfo.specialityInfo!!) {
//            appointtype = appointtype + "," + model.name.toString()
//        }
//
//        if (appointtype.isNotEmpty()) {
//            holder.appointment_type.text = appointtype.substring(1, appointtype.length).trim()
//        }

        textView_type

        if (mDataDocInfo.gender == InterConsts.FEMALE) {
            imgGender.setImageDrawable(resources.getDrawable(R.drawable.ic_gender))
        } else {
            imgGender.setImageDrawable(resources.getDrawable(R.drawable.ic_male))
        }
        imageView_doctor_profile.loadUserPhoto(WS_BASE_DOCTOR_IMAGE_URL + mDataDocInfo.profile_pic)
    }

}
