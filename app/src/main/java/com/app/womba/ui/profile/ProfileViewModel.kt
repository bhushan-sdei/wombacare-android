package com.app.womba.ui.profile

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.womba.base.BaseModel
import com.app.womba.base.BaseViewModel
import com.app.womba.model.ProfileImageModel
import com.app.womba.repository.AuthRepository
import com.app.womba.ui.splash.BaseNavigator

class ProfileViewModel(application: Application) :
    BaseViewModel<BaseNavigator>(application = application) {

    private val mRepository: AuthRepository = AuthRepository()
    private var mModel: MutableLiveData<ProfileImageModel>? = null

    fun getResponse(): MutableLiveData<ProfileImageModel> {
        if (mModel == null) {
            mModel = MutableLiveData()
        }
        return mModel as MutableLiveData<ProfileImageModel>
    }

    fun updateImage(model: String) {
        mRepository.updateImage(model) {
            mModel!!.value = it
        }
    }

}