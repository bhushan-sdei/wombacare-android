package com.app.womba.ui.forgetPassword

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.womba.base.BaseViewModel
import com.app.womba.model.UserModel
import com.app.womba.model.postResponseModel.ForgetPost
import com.app.womba.repository.AuthRepository
import com.app.womba.ui.splash.BaseNavigator

class ForgetPasswordVM(application: Application) :
    BaseViewModel<BaseNavigator>(application = application) {

    private val mRepository: AuthRepository = AuthRepository()
    private var mModel: MutableLiveData<UserModel>? = null

    fun getResponse(): MutableLiveData<UserModel> {
        if (mModel == null) {
            mModel = MutableLiveData()
        }
        return mModel as MutableLiveData<UserModel>
    }

    fun forgetPassword(model: ForgetPost) {
        setIsLoading(true)
        mRepository.forgetPassword(model) {
            setIsLoading(false)
            mModel!!.value = it
        }
    }

}