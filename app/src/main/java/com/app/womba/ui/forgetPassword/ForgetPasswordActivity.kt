package com.app.womba.ui.forgetPassword

import android.content.Context
import android.text.TextUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.womba.BR
import com.app.womba.R
import com.app.womba.base.BaseActivity
import com.app.womba.databinding.ActivityForgetPasswordBinding
import com.app.womba.model.UserModel
import com.app.womba.model.postResponseModel.ForgetPost
import com.app.womba.utils.InterConsts
import com.app.womba.utils.customViews.LoadingDialog
import com.app.womba.utils.responseHandler
import com.app.womba.utils.responseHandlerToast
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import kotlinx.android.synthetic.main.activity_forget_password.*

class ForgetPasswordActivity : BaseActivity<ActivityForgetPasswordBinding, ForgetPasswordVM>() {

    override val viewModel: ForgetPasswordVM
        get() = ViewModelProviders.of(this).get(ForgetPasswordVM::class.java)
    override val layoutId: Int = R.layout.activity_forget_password
    override val bindingVariable: Int = BR.viewModel
    override val context: Context = this


    private fun validatingRequired(): Boolean {
        var message = ""
        if (TextUtils.isEmpty(textInputEditText_email.text.toString())) {
            message = resources.getString(R.string.EmailErrorMessage)
            textInputLayout_email.error = message
        } else if (!textInputEditText_email.text.toString().validEmail()) {
            message = resources.getString(R.string.invalid_email)
            textInputLayout_email.error = message
        } else {
            textInputLayout_email.error = null
        }

        return message.equals("", ignoreCase = true)
    }

    override fun onCreate() {
    }

    override fun initListeners() {
        button_signup.setOnClickListener {
            if (validatingRequired()) {
                mViewModel!!.forgetPassword(
                    ForgetPost(
                        textInputEditText_email.text.toString(),
                        InterConsts.USER_TYPE
                    )
                )
            }
        }
        textView_let_me_in.setOnClickListener {
            finish()
        }

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
                if (mData != null && responseHandlerToast(mData.code, mData.message)) {
                    finish()
                }
            })
    }

}
