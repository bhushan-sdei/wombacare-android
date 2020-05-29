package com.app.womba.ui.vitalHistory

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.womba.base.BaseViewModel
import com.app.womba.model.VitalHistoryData
import com.app.womba.model.VitalsTrendResponse
import com.app.womba.model.postResponseModel.ScanHistoryPostModel
import com.app.womba.model.postResponseModel.VitalPost
import com.app.womba.repository.VitalRepository
import com.app.womba.ui.splash.BaseNavigator

class VitalLogViewModel (application: Application) :
    BaseViewModel<BaseNavigator>(application = application) {

    private val mRepository: VitalRepository = VitalRepository()
    private var mModel: MutableLiveData<VitalHistoryData>? = null

    fun getResponse(): MutableLiveData<VitalHistoryData> {
        if (mModel == null) {
            mModel = MutableLiveData()
        }
        return mModel as MutableLiveData<VitalHistoryData>
    }

    fun getEvitalsHistorydata(model: ScanHistoryPostModel) {
        setIsLoading(true)
        mRepository.getEvitalsHistorydata(model) {
            setIsLoading(false)
            mModel!!.value = it
        }
    }
}
