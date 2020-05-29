package com.app.womba.ui.profile.basicProfile

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.womba.base.BaseModel
import com.app.womba.base.BaseViewModel
import com.app.womba.model.LanguageModel
import com.app.womba.model.UserModel
import com.app.womba.model.postResponseModel.PatientId
import com.app.womba.model.postResponseModel.UpdateProfilePost
import com.app.womba.repository.AuthRepository
import com.app.womba.ui.splash.BaseNavigator
import com.app.womba.utils.getUserData

class BasicProfileVM(application: Application) : BaseViewModel<BaseNavigator>(application = application) {


    private val mRepository: AuthRepository = AuthRepository()
    private var mModel: MutableLiveData<UserModel>? = null
    private var mModelLanguage: MutableLiveData<LanguageModel>? = null

    fun getResponse(): MutableLiveData<UserModel> {
        if (mModel == null) {
            mModel = MutableLiveData()
        }
        return mModel as MutableLiveData<UserModel>
    }

    fun getLanguage(): MutableLiveData<LanguageModel> {
        if (mModelLanguage == null) {
            mModelLanguage = MutableLiveData()
            hitLanguageApi()
        }
        return mModelLanguage as MutableLiveData<LanguageModel>
    }

    fun hitUpdateProfile(model: UpdateProfilePost) {
        setIsLoading(true)
        mRepository.updateProfile(model) {
            hitGetProfile(PatientId(getUserData().data!!.get_id()!!))
        }
    }

    fun hitGetProfile(model: PatientId) {
        setIsLoading(true)
        mRepository.getProfile(model) {
            setIsLoading(false)
            mModel!!.value = it
        }
    }

    fun hitLanguageApi() {
        setIsLoading(true)
        mRepository.getLanguage {
            setIsLoading(false)
            mModelLanguage!!.value = it
        }
    }


}