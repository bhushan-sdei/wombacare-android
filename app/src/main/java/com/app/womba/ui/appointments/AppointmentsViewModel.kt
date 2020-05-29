package com.app.womba.ui.appointments

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.womba.base.BaseViewModel
import com.app.womba.model.AppointmentModel
import com.app.womba.repository.PatientRepository
import com.app.womba.ui.splash.BaseNavigator

class AppointmentsViewModel  (application: Application) :
    BaseViewModel<BaseNavigator>(application = application) {

    private val mRepository: PatientRepository = PatientRepository()
    private var mModel: MutableLiveData<AppointmentModel>? = null

    fun getResponse(): MutableLiveData<AppointmentModel> {
        if (mModel == null) {
            mModel = MutableLiveData()
        }
        return mModel as MutableLiveData<AppointmentModel>
    }
}