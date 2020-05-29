package com.app.womba.ui.changePassword

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.womba.base.BaseModel
import com.app.womba.base.BaseViewModel
import com.app.womba.model.DoctorListResponse
import com.app.womba.model.postResponseModel.ChangePassword
import com.app.womba.model.postResponseModel.ListPostResponse
import com.app.womba.model.postResponseModel.RegistrationPost
import com.app.womba.repository.AuthRepository
import com.app.womba.repository.DoctorRepository
import com.app.womba.ui.splash.BaseNavigator

class ChangePasswordVM (application: Application) :
    BaseViewModel<BaseNavigator>(application = application) {

    private val mRepository: AuthRepository = AuthRepository()
    private var mModel: MutableLiveData<BaseModel>? = null

    fun getResponse(): MutableLiveData<BaseModel> {
        if (mModel == null) {
            mModel = MutableLiveData()
        }
        return mModel as MutableLiveData<BaseModel>
    }

    fun hitApi(model: ChangePassword) {
        setIsLoading(true)
        mRepository.userChangePassword(model) {
            setIsLoading(false)
            mModel!!.value = it
        }
    }

}