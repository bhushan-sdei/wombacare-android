package com.app.womba.ui.registration

import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.womba.BR
import com.app.womba.R
import com.app.womba.model.postResponseModel.RegistrationPost
import com.app.womba.base.BaseActivity
import com.app.womba.base.BaseModel
import com.app.womba.databinding.ActivitySplashBinding
import com.app.womba.utils.customViews.LoadingDialog
import com.app.womba.utils.isValidPassword
import com.app.womba.utils.responseHandlerToast
import com.app.womba.utils.showOkayAlert
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: BaseActivity<ActivitySplashBinding, RegistrationVM>(){

    override val viewModel: RegistrationVM
        get() = ViewModelProviders.of(this).get(RegistrationVM::class.java)
    override val layoutId: Int = R.layout.activity_register
    override val bindingVariable: Int = BR.viewModel
    override val context: Context = this

    override fun onCreate() {

        button_signup.setOnClickListener {
            if(validatingRequired()) {
               mViewModel?.hitApi(RegistrationPost(textInputEditText_email.text.toString(),
                   textInputEditText_psd.text.toString(),
                   cpCountryCode.selectedCountryCodeWithPlus + edPhoneNumber.text.toString()))
            }
        }

        textView_let_me_in.setOnClickListener {
            finish()
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
            Observer<BaseModel> { mData ->
                if (responseHandlerToast(mData.code, mData.message)) {
                    finish()
                }
            })

    }

    private fun validatingRequired(): Boolean {
        var message = ""
        //validate the content
        if (TextUtils.isEmpty(textInputEditText_email.text.toString())) {
            message = resources.getString(R.string.invalid_email)
            textInputLayout_email.error = message
        } else if (!textInputEditText_email.text.toString().validEmail()) {
            message = resources.getString(R.string.invalid_email)
            textInputLayout_email.error = message
        } else {
            textInputLayout_email.error = null
        }
        if (TextUtils.isEmpty(textInputEditText_psd.text.toString())) {
            message = resources.getString(R.string.missing_password)
            textInputLayout_psd.error = message
        } else {
            if (!isValidPassword(textInputEditText_psd.text.toString().trim())) {
                message = resources.getString(R.string.password_validation_error)
                textInputLayout_psd.error = message
            }else {
                textInputLayout_psd.error = null
            }
        }



        if (TextUtils.isEmpty(edPhoneNumber.text.toString())) {
            message = resources.getString(R.string.missing_phone)
            txtInputPhone.error = message
        } else {
            txtInputPhone.error = null
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
