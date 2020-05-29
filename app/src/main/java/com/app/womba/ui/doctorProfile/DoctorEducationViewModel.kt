package com.app.womba.ui.doctorProfile

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.womba.base.BaseViewModel
import com.app.womba.model.DoctorEducationResponse
import com.app.womba.model.postResponseModel.DoctorEducationPost
import com.app.womba.repository.DoctorRepository
import com.app.womba.ui.splash.BaseNavigator

class DoctorEducationViewModel  (application: Application) :
    BaseViewModel<BaseNavigator>(application = application) {

    private val mRepository: DoctorRepository = DoctorRepository()
    private var mModel: MutableLiveData<DoctorEducationResponse>? = null

    fun getResponse(): MutableLiveData<DoctorEducationResponse> {
        if (mModel == null) {
            mModel = MutableLiveData()
        }
        return mModel as MutableLiveData<DoctorEducationResponse>
    }

    fun educationList(doctorEducationPost: DoctorEducationPost) {
        mRepository.educationList(doctorEducationPost) {
            mModel!!.value = it
        }
    }
}
