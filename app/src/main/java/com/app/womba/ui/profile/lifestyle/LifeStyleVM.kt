package com.app.womba.ui.profile.lifestyle

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.womba.base.BaseModel
import com.app.womba.base.BaseViewModel
import com.app.womba.model.LifeStylePost
import com.app.womba.model.PatientLifeStyleModel
import com.app.womba.model.healthProfile.HealthProfileModel
import com.app.womba.repository.HealthProfileRepository
import com.app.womba.ui.splash.BaseNavigator

class LifeStyleVM(application: Application) : BaseViewModel<BaseNavigator>(application = application) {


    private val mRepository: HealthProfileRepository = HealthProfileRepository()
    private var mModel: MutableLiveData<BaseModel>? = null
    private var mMedicalData: MutableLiveData<PatientLifeStyleModel>? = null

    fun getResponse(): MutableLiveData<BaseModel> {
        if (mModel == null) {
            mModel = MutableLiveData()
        }
        return mModel as MutableLiveData<BaseModel>
    }

    fun getPatientLifeStyleResponse(): MutableLiveData<PatientLifeStyleModel> {
        if (mMedicalData == null) {
            mMedicalData = MutableLiveData()
        }
        return mMedicalData as MutableLiveData<PatientLifeStyleModel>
    }


    fun savePatientLifeStyle(model:LifeStylePost) {
        setIsLoading(true)
        mRepository.savePatientLifeStyle(model) {
            setIsLoading(false)
            mModel!!.value = it
        }
    }


    fun getPatientLifeStyle() {
        setIsLoading(true)
        mRepository.getPatientLifeStyle() {
            setIsLoading(false)
            mMedicalData!!.value = it
        }
    }

}