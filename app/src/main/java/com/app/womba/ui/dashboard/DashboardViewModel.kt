package com.app.womba.ui.dashboard

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.womba.base.BaseViewModel
import com.app.womba.model.AppointmentModel
import com.app.womba.repository.PatientRepository
import com.app.womba.ui.splash.BaseNavigator

class DashboardViewModel (application: Application) :
    BaseViewModel<BaseNavigator>(application = application) {

    private val mRepository: PatientRepository = PatientRepository()
    private var mModel: MutableLiveData<AppointmentModel>? = null

    fun getResponse(): MutableLiveData<AppointmentModel> {
        if (mModel == null) {
            mModel = MutableLiveData()
        }
        return mModel as MutableLiveData<AppointmentModel>
    }

    fun getUpcomingAppointments() {
        mRepository.getUpcomingAppointments("1") {
            mModel!!.value = it
        }
    }

    fun getPastAppointments() {
        mRepository.getPastAppointments {
//            mModel!!.value = it
        }
    }
}
