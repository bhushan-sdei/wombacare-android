package com.app.womba.ui.payment

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.womba.base.BaseViewModel
import com.app.womba.model.BookingResponse
import com.app.womba.model.postResponseModel.PaymentModel
import com.app.womba.repository.DoctorRepository
import com.app.womba.ui.splash.BaseNavigator

class PaymentVM(application: Application) :
    BaseViewModel<BaseNavigator>(application = application) {

    private val mRepository: DoctorRepository = DoctorRepository()
    private var mModel: MutableLiveData<BookingResponse>? = null

    fun getResponse(): MutableLiveData<BookingResponse> {
        if (mModel == null) {
            mModel = MutableLiveData()
        }
        return mModel as MutableLiveData<BookingResponse>
    }


    fun bookAppointment(model: PaymentModel) {
        setIsLoading(true)
        mRepository.bookAppointment(model) {
            setIsLoading(false)
            mModel!!.value = it
        }
    }
}