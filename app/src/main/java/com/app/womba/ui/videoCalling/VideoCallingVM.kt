package com.app.womba.ui.videoCalling

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.womba.base.BaseModel
import com.app.womba.base.BaseViewModel
import com.app.womba.model.AppointmentModel
import com.app.womba.model.VideoTokenModel
import com.app.womba.model.postResponseModel.GenerateToken
import com.app.womba.repository.PatientRepository
import com.app.womba.ui.splash.BaseNavigator

class VideoCallingVM (application: Application) :
    BaseViewModel<BaseNavigator>(application = application) {

    private val mRepository: PatientRepository = PatientRepository()
    private var mModel: MutableLiveData<VideoTokenModel>? = null

    fun getResponse(): MutableLiveData<VideoTokenModel> {
        if (mModel == null) {
            mModel = MutableLiveData()
        }
        return mModel as MutableLiveData<VideoTokenModel>
    }

    fun generateToken(model:GenerateToken) {
        mRepository.generateToken(model) {
            mModel!!.value = it
        }
    }
}