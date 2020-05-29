package com.app.womba.ui.profile.healthProfile

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.womba.base.BaseModel
import com.app.womba.base.BaseViewModel
import com.app.womba.model.healthProfile.HealthProfileModel
import com.app.womba.model.postResponseModel.*
import com.app.womba.repository.HealthProfileRepository
import com.app.womba.ui.splash.BaseNavigator

class HealthProfileVM(application: Application) :
    BaseViewModel<BaseNavigator>(application = application) {

    private val mRepository: HealthProfileRepository = HealthProfileRepository()
    private var mModel: MutableLiveData<BaseModel>? = null
    private var mModelDeleteMedical: MutableLiveData<BaseModel>? = null
    private var mMedicalData: MutableLiveData<HealthProfileModel>? = null

    fun getResponse(): MutableLiveData<BaseModel> {
        if (mModel == null) {
            mModel = MutableLiveData()
        }
        return mModel as MutableLiveData<BaseModel>
    }

    fun getDeleteResponse(): MutableLiveData<BaseModel> {
        if (mModelDeleteMedical == null) {
            mModelDeleteMedical = MutableLiveData()
        }
        return mModelDeleteMedical as MutableLiveData<BaseModel>
    }

    fun getMedicalData(): MutableLiveData<HealthProfileModel> {
        if (mMedicalData == null) {
            mMedicalData = MutableLiveData()
        }
        return mMedicalData as MutableLiveData<HealthProfileModel>
    }

    fun getallMedicalData() {
        setIsLoading(true)
        mRepository.getallMedicalData() {
            setIsLoading(false)
            mMedicalData!!.value = it
        }
    }
   fun addMedicalConditions(model: ConditionPost) {
        setIsLoading(true)
        mRepository.addMedicalConditions(model) {
            setIsLoading(false)
            mModel!!.value = it
        }
    }

    fun addMedicalMedications(model: MedicationsPost) {
        setIsLoading(true)
        mRepository.addMedicalMedications(model) {
            setIsLoading(false)
            mModel!!.value = it
        }
    }

    fun addMedicalAlergies(model: AlergiesPost) {
        setIsLoading(true)
        mRepository.addMedicalAlergies(model) {
            setIsLoading(false)
            mModel!!.value = it
        }
    }

    fun addMedicalTreatments(model: TreatmentPost) {
        setIsLoading(true)
        mRepository.addMedicalTreatments(model) {
            setIsLoading(false)
            mModel!!.value = it
        }
    }

    fun addMedicalImmunization(model: ImmunizationPost) {
        setIsLoading(true)
        mRepository.addMedicalImmunization(model) {
            setIsLoading(false)
            mModel!!.value = it
        }
    }

    fun addMedicalCovid(model: MedicalCovidPost) {
        setIsLoading(true)
        mRepository.addMedicalCovid(model) {
            setIsLoading(false)
            mModel!!.value = it
        }
    }

    fun deleteMedicalRecord(model: DeleteMedical) {
        setIsLoading(true)
        mRepository.deleteMedicalRecord(model) {
            setIsLoading(false)
            mModelDeleteMedical!!.value = it
        }
    }

}