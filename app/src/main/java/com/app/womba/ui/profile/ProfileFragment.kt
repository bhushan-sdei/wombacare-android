package com.app.womba.ui.profile

import `in`.mayanknagwanshi.imagepicker.ImageSelectActivity
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.afollestad.assent.Permission
import com.afollestad.assent.askForPermissions
import com.app.womba.R
import com.app.womba.model.ProfileImageModel
import com.app.womba.model.postResponseModel.ChangePassword
import com.app.womba.network.WS_BASE_PATIENT_IMAGE_URL
import com.app.womba.ui.binah.VitalScanActivity
import com.app.womba.ui.changePassword.ChangePasswordActivity
import com.app.womba.ui.profile.basicProfile.BasicProfileActivity
import com.app.womba.ui.profile.healthProfile.HealthProfileActivity
import com.app.womba.ui.profile.lifestyle.LifeStyleActivity
import com.app.womba.ui.vitalHistory.VitalHistoryActivity
import com.app.womba.utils.*
import com.app.womba.utils.customViews.LoadingDialog
import kotlinx.android.synthetic.main.profile_fragment.*
import java.io.File


class ProfileFragment : Fragment() {

    private val REQUEST_CODE: Int = 1002
    var filePath = ""

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        txtDocName.text = getUserData().data!!.firstname + " " + getUserData().data!!.lastname

        if (getUserData().data!!.profile_pic!!.contains("http")) {
            imgProfile.loadUserPhoto(getUserData().data!!.profile_pic)

        } else {
            imgProfile.loadUserPhoto(WS_BASE_PATIENT_IMAGE_URL + getUserData().data!!.profile_pic)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel

        viewModel.getLoader().observe(this,
            Observer { mData ->
                if (mData) {
                    LoadingDialog.getLoader().showLoader(activity!!)
                } else {
                    LoadingDialog.getLoader().dismissLoader()
                }
            })

        viewModel.getResponse().observe(this,
            Observer<ProfileImageModel> { mData ->
                if (mData != null && activity!!.responseHandler(mData.code, mData.message)) {
                    val usermodel = getUserData()
                    usermodel.data!!.profile_pic = mData.imageData!!
                    saveUserData(usermodel)
                }
            })


        imgAdd.setOnClickListener {
            val intent = Intent(activity, ImageSelectActivity::class.java)
            intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true) //default is true
            intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true) //default is true
            intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true) //default is true
            startActivityForResult(intent, REQUEST_CODE)
        }

        txtBasicProfile.setOnClickListener {
            activity!!.navigateClass<BasicProfileActivity>()
        }

        txtHealthProfile.setOnClickListener {
            activity!!.navigateClass<HealthProfileActivity>()
        }

        txtLifeStyle.setOnClickListener {
            activity!!.navigateClass<LifeStyleActivity>()
        }

        txtVitalsHistory.setOnClickListener {
            activity!!.navigateClass<VitalHistoryActivity>()
        }

        txtChangePassword.setOnClickListener {
            activity!!.navigateClass<ChangePasswordActivity>()
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
                        activity!!.showSnackBarPermission(txtVitalsHistory, activity!!)
                    } else {

                        activity!!.navigateClass<VitalScanActivity>()
                    }
                }
            } else {
                activity!!.navigateClass<VitalScanActivity>()
            }
        }

        btnLogout.setOnClickListener {
            activity!!.showLogoutAlert(
                activity!!,
                "Logout Alert",
                "Are you sure you want to logout?"
            )
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                val filePathFirst = data!!.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH)
                filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH)!!
                viewModel.updateImage(filePath)
                val imgFile = File(filePathFirst!!);
                if (imgFile.exists()) {
                    val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                    imgProfile.setImageBitmap(myBitmap)
                }
            }
        }
    }


}
