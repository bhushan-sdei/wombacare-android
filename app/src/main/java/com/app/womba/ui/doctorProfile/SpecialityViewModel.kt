package com.app.womba.ui.doctorProfile

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.womba.base.BaseViewModel
import com.app.womba.model.DoctorEducationResponse
import com.app.womba.repository.DoctorRepository
import com.app.womba.ui.splash.BaseNavigator

class SpecialityViewModel (application: Application) :
    BaseViewModel<BaseNavigator>(application = application) {

    private val mRepository: DoctorRepository = DoctorRepository()
    private var mModel: MutableLiveData<DoctorEducationResponse>? = null

    fun getResponse(): MutableLiveData<DoctorEducationResponse> {
        if (mModel == null) {
            mModel = MutableLiveData()
        }
        return mModel as MutableLiveData<DoctorEducationResponse>
    }

//    fun specialityList(doctorEducationPost: DoctorSpecialityPost) {
//        mRepository.specialityList(doctorEducationPost) {
//            mModel!!.value = it
//        }
//    }
}