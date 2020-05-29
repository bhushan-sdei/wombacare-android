package com.app.womba.ui.payments

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.womba.base.BaseViewModel
import com.app.womba.model.AppointmentModel
import com.app.womba.model.BookingResponse
import com.app.womba.model.PaymentListModel
import com.app.womba.model.postResponseModel.PaymentModel
import com.app.womba.repository.DoctorRepository
import com.app.womba.repository.PatientRepository
import com.app.womba.ui.splash.BaseNavigator

class PaymentsViewModel(application: Application) :
    BaseViewModel<BaseNavigator>(application = application) {

    private val mRepository: PatientRepository = PatientRepository()
    private var mModel: MutableLiveData<PaymentListModel>? = null

    fun getResponse(): MutableLiveData<PaymentListModel> {
        if (mModel == null) {
            mModel = MutableLiveData()
        }
        return mModel as MutableLiveData<PaymentListModel>
    }

    fun getPaymentListing(){
        mRepository.getPaymentsList {
            mModel!!.value=it
        }
    }


}