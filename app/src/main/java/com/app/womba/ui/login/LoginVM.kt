package com.app.womba.ui.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.womba.base.BaseViewModel
import com.app.womba.model.UserModel
import com.app.womba.model.postResponseModel.LoginPost
import com.app.womba.model.postResponseModel.PatientId
import com.app.womba.repository.AuthRepository
import com.app.womba.ui.splash.BaseNavigator
import com.app.womba.utils.InterConsts
import com.app.womba.utils.getAppPref
import com.app.womba.utils.saveUserData

class LoginVM(application: Application) :
    BaseViewModel<BaseNavigator>(application = application) {

    private val mRepository: AuthRepository = AuthRepository()
    private var mModel: MutableLiveData<UserModel>? = null

    fun getResponse(): MutableLiveData<UserModel> {
        if (mModel == null) {
            mModel = MutableLiveData()
        }
        return mModel as MutableLiveData<UserModel>
    }

    fun hitApi(model: LoginPost) {
        setIsLoading(true)
        mRepository.login(model) {
            try {
                saveUserData(it)
                getAppPref().setString(InterConsts.AUTH_TOKEN, it.data!!.token!!)
                mRepository.getProfile(PatientId(it.data!!.get_id()!!)) {
                    setIsLoading(false)
                    mModel!!.value = it
                }
            } catch (e: Exception) {
                getAppPref().clearShf()
                setIsLoading(false)
                mModel!!.value = it
            }

        }
    }

}