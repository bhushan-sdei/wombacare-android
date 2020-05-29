package com.app.womba.ui.bookAppointment

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.womba.base.BaseViewModel
import com.app.womba.model.DoctorListResponse
import com.app.womba.model.postResponseModel.ListPostResponse
import com.app.womba.repository.DoctorRepository
import com.app.womba.ui.splash.BaseNavigator

class BookAppointmentsViewModel (application: Application) :
    BaseViewModel<BaseNavigator>(application = application) {

    private val mRepository: DoctorRepository = DoctorRepository()
    private var mModel: MutableLiveData<DoctorListResponse>? = null

    fun getResponse(): MutableLiveData<DoctorListResponse> {
        if (mModel == null) {
            mModel = MutableLiveData()
        }
        return mModel as MutableLiveData<DoctorListResponse>
    }

    fun getAllDoctorsList(model:ListPostResponse) {
        setIsLoading(true)
        mRepository.getAllDoctorsList(model) {
            setIsLoading(false)
            mModel!!.value = it
        }
    }

}
