package com.app.womba.ui.splash

import android.app.Application
import android.os.Handler
import com.app.womba.base.BaseViewModel

class SplashVM(application: Application) : BaseViewModel<BaseNavigator>(application = application) {

    /** Duration of wait  */
    private val SPLASH_DISPLAY_LENGTH: Long = 3000

    fun startTimer() {
        Handler().postDelayed({
            mNavigator.openActivity()
        }, SPLASH_DISPLAY_LENGTH)
    }

}