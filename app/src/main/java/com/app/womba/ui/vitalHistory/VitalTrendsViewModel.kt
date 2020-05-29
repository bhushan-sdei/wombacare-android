package com.app.womba.ui.vitalHistory

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.womba.base.BaseModel
import com.app.womba.base.BaseViewModel
import com.app.womba.model.TrendSubModel
import com.app.womba.model.VitalsTrendResponse
import com.app.womba.model.postResponseModel.VitalPost
import com.app.womba.repository.VitalRepository
import com.app.womba.ui.splash.BaseNavigator

class VitalTrendsViewModel (application: Application) :
    BaseViewModel<BaseNavigator>(application = application) {

    private val mRepository: VitalRepository = VitalRepository()
    private var mModel: MutableLiveData<VitalsTrendResponse>? = null

    fun getResponse(): MutableLiveData<VitalsTrendResponse> {
        if (mModel == null) {
            mModel = MutableLiveData()
        }
        return mModel as MutableLiveData<VitalsTrendResponse>
    }

    fun getTrendHistory(model: VitalPost) {
        setIsLoading(true)
        mRepository.getSaveEVitalsDataGraph(model) {
            setIsLoading(false)
            mModel!!.value = it
        }
    }
}