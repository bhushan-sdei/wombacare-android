package com.app.womba.ui.notification

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.app.womba.base.BaseModel
import com.app.womba.base.BaseViewModel
import com.app.womba.model.NotificationListModel
import com.app.womba.model.UserModel
import com.app.womba.model.postResponseModel.LoginPost
import com.app.womba.model.postResponseModel.PatientId
import com.app.womba.repository.AuthRepository
import com.app.womba.repository.PatientRepository
import com.app.womba.ui.splash.BaseNavigator
import com.app.womba.utils.InterConsts
import com.app.womba.utils.getAppPref
import com.app.womba.utils.saveUserData

class NotificationVM (application: Application) :
    BaseViewModel<BaseNavigator>(application = application) {

    private val mRepository: PatientRepository = PatientRepository()
    private var mModel: MutableLiveData<NotificationListModel>? = null

    fun getResponse(): MutableLiveData<NotificationListModel> {
        if (mModel == null) {
            mModel = MutableLiveData()
        }
        return mModel as MutableLiveData<NotificationListModel>
    }

    fun getNotificationList() {
        setIsLoading(true)
        mRepository.getNotificationList() {
            setIsLoading(false)
            mModel!!.value=it

        }
    }

}