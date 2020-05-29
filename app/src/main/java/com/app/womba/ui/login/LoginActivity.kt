package com.app.womba.ui.login

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.text.TextUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.womba.BR
import com.app.womba.R
import com.app.womba.base.BaseActivity
import com.app.womba.databinding.ActivityLoginBinding
import com.app.womba.model.UserModel
import com.app.womba.model.postResponseModel.LoginPost
import com.app.womba.ui.forgetPassword.ForgetPasswordActivity
import com.app.womba.ui.landing.LandingActivity
import com.app.womba.ui.registration.RegisterActivity
import com.app.womba.utils.*
import com.app.womba.utils.customViews.LoadingDialog
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.textInputEditText_psd
import kotlinx.android.synthetic.main.activity_login.textInputLayout_email
import kotlinx.android.synthetic.main.activity_login.textInputLayout_psd
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginVM>() {

    override val viewModel: LoginVM
        get() = ViewModelProviders.of(this).get(LoginVM::class.java)
    override val layoutId: Int = R.layout.activity_login
    override val bindingVariable: Int = BR.viewModel
    override val context: Context = this

    var mDeviceToken = ""

    override fun onCreate() {
        FirebaseApp.initializeApp(mContext)
        FirebaseMessaging.getInstance().isAutoInitEnabled = true

        getDeviceToken(this) {
            mDeviceToken = it
        }
    }

    @SuppressLint("HardwareIds")
    override fun initListeners() {
        mViewModel!!.getLoader().observe(this,
            Observer { mData ->
                if (mData) {
                    LoadingDialog.getLoader().showLoader(this)
                } else {
                    LoadingDialog.getLoader().dismissLoader()
                }
            })

        mViewModel!!.getResponse().observe(this,
            Observer<UserModel> { mData ->
                if (mData != null && responseHandler(mData.code, mData.message)) {
                    saveUserData(mData)
                    if (mData.data!!.firstname!!.trim().isEmpty()) {
                        navigateClass<LoginCompleteActivity>()
                    } else {
                        navigateClass<LandingActivity>()
                    }
                    finishAffinity()
                }
            })


        button_login.setOnClickListener {
            if (validatingRequired()) {
                mViewModel?.hitApi(
                    LoginPost(
                        textInputeditText_email.text.toString(),
                        textInputEditText_psd.text.toString(),
                        InterConsts.USER_TYPE,
                        Settings.Secure.getString(
                            this@LoginActivity.contentResolver,
                            Settings.Secure.ANDROID_ID
                        ),
                        mDeviceToken,
                        mDeviceToken,
                        InterConsts.DEVICE_TYPE
                    )
                )
            }
        }

        textView_forgtepsd.setOnClickListener {
            navigateClass<ForgetPasswordActivity>()
        }
        textView_register.setOnClickListener {
            navigateClass<RegisterActivity>()
        }



    }

    private fun validatingRequired(): Boolean {
        var message = ""
        if (TextUtils.isEmpty(textInputeditText_email.text.toString())) {
            message = resources.getString(R.string.EmailErrorMessage)
            textInputLayout_email.error = message
        } else if (!textInputeditText_email.text.toString().validEmail()) {
            message = resources.getString(R.string.invalid_email)
            textInputLayout_email.error = message
        } else {
            textInputLayout_email.error = null
        }

        if (TextUtils.isEmpty(textInputEditText_psd.text.toString())) {
            message = resources.getString(R.string.PasswordErrorMessage)
            textInputLayout_psd.error = message
        } else {
            if (!isValidPassword(textInputEditText_psd.text.toString().trim())) {
                message = resources.getString(R.string.password_validation_error)
                textInputLayout_psd.error = message
            }else {
                textInputLayout_psd.error = null
            }
        }
        return message.equals("", ignoreCase = true)
    }

}
