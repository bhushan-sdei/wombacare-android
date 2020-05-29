package com.app.womba.ui.changePassword

import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.womba.BR
import com.app.womba.R
import com.app.womba.base.BaseActivity
import com.app.womba.databinding.ActivityChangePasswordBinding
import com.app.womba.model.postResponseModel.ChangePassword
import com.app.womba.ui.splash.SplashActivity
import com.app.womba.utils.*
import com.app.womba.utils.customViews.LoadingDialog
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.activity_change_password.imgInfo
import kotlinx.android.synthetic.main.activity_change_password.textInputEditText_confirm_psd
import kotlinx.android.synthetic.main.activity_change_password.textInputEditText_psd
import kotlinx.android.synthetic.main.activity_change_password.textInputLayout_psd

class ChangePasswordActivity : BaseActivity<ActivityChangePasswordBinding, ChangePasswordVM>() {

    override val viewModel: ChangePasswordVM
        get() = ViewModelProviders.of(this).get(ChangePasswordVM::class.java)
    override val layoutId: Int = R.layout.activity_change_password
    override val bindingVariable: Int = BR.viewModel
    override val context: Context = this

    override fun onCreate() {
        mViewModel!!.getLoader().observe(this,
            Observer { mData ->
                if (!mData) {
                    LoadingDialog.getLoader().dismissLoader()
                } else {
                    LoadingDialog.getLoader().showLoader(this)
                }
            })

        mViewModel!!.getResponse().observe(this,
            Observer { mData ->
                if (mData != null && responseHandler(mData.code, mData.message)) {
                    showToast("Login Again")
                    getAppPref().clearShf()
                    this.navigateClass<SplashActivity>()
                    this.finishAffinity()
                }
            })
    }

    override fun initListeners() {
        btnChangePassword.setOnClickListener {
            if (validatingRequired()) {
                viewModel.hitApi(
                    ChangePassword(
                        getUserData().data!!.get_id()!!,
                        tvOldPassword.text.toString(),
                        textInputEditText_psd.text.toString()
                    )
                )
            }
        }

        imgInfo.setOnClickListener {
            showOkayAlert(
                this,
                getString(R.string.app_name),
                getString(R.string.password_validation_error),
                getString(R.string.ok),
                DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.dismiss()
                })
        }

    }

    private fun validatingRequired(): Boolean {
        var message = ""
        //validate the content

        if (TextUtils.isEmpty(tvOldPassword.text.toString())) {
            message = resources.getString(R.string.missing_password)
            til_old_password.error = message
        } else {
            if (!isValidPassword(tvOldPassword.text.toString().trim())) {
                message = resources.getString(R.string.password_validation_error)
                til_old_password.error = message
            } else {
                til_old_password.error = null
            }
        }

        if (TextUtils.isEmpty(textInputEditText_psd.text.toString())) {
            message = resources.getString(R.string.missing_password)
            textInputLayout_psd.error = message
        } else {
            if (!isValidPassword(textInputEditText_psd.text.toString().trim())) {
                message = resources.getString(R.string.password_validation_error)
                textInputLayout_psd.error = message
            } else {
                textInputLayout_psd.error = null
            }
        }

        when {
            TextUtils.isEmpty(textInputEditText_confirm_psd.text.toString()) -> {
                message = resources.getString(R.string.missing_confirm_password)
                textInputEditText_confirm_psd.error = message
            }
            textInputEditText_psd.text.toString() != textInputEditText_confirm_psd.text.toString() -> {
                message = resources.getString(R.string.not_equal_confirm_password)
                textInputEditText_confirm_psd.error = message
            }
            else -> {
                textInputEditText_confirm_psd.error = null
            }
        }
        return message.equals("", ignoreCase = true)
    }


}
