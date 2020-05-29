package com.app.womba.ui.binah

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.womba.base.BaseModel
import com.app.womba.base.BaseViewModel
import com.app.womba.repository.BinahRepository
import com.app.womba.ui.splash.BaseNavigator
import com.app.womba.model.fullreport.HealthModel

class VitalDetailVM(application: Application) :
    BaseViewModel<BaseNavigator>(application = application) {

    private val mRepository: BinahRepository = BinahRepository()
    private var mModel: MutableLiveData<BaseModel>? = null

    fun getResponse(): MutableLiveData<BaseModel> {
        if (mModel == null) {
            mModel = MutableLiveData()
        }
        return mModel as MutableLiveData<BaseModel>
    }

    fun saveEvitalsData(model: HealthModel) {
        setIsLoading(true)
        mRepository.saveEvitalsData(model) {
            setIsLoading(false)
            mModel!!.value=it
        }
    }


}