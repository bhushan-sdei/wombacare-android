package com.app.womba.ui.doctorProfile

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.womba.base.BaseViewModel
import com.app.womba.model.DoctorSlotModel
import com.app.womba.model.postResponseModel.DoctorSlotTimePost
import com.app.womba.repository.DoctorRepository
import com.app.womba.ui.splash.BaseNavigator

class DoctorTimeSlotVM (application: Application) :
    BaseViewModel<BaseNavigator>(application = application) {


    private val mRepository: DoctorRepository = DoctorRepository()
    private var mModel: MutableLiveData<DoctorSlotModel>? = null

    fun getResponse(): MutableLiveData<DoctorSlotModel> {
        if (mModel == null) {
            mModel = MutableLiveData()
        }
        return mModel as MutableLiveData<DoctorSlotModel>
    }

    fun getDoctorSlotByDate(model: DoctorSlotTimePost) {
        setIsLoading(true)
        mRepository.getDoctorSlotByDate(model) {
            setIsLoading(false)
            mModel!!.value = it
        }
    }

}