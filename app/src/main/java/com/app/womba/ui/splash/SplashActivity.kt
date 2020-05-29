package com.app.womba.ui.splash

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.app.womba.BR
import com.app.womba.R
import com.app.womba.base.BaseActivity
import com.app.womba.databinding.ActivitySplashBinding
import com.app.womba.ui.intro.IntroActivity
import com.app.womba.ui.landing.LandingActivity
import com.app.womba.ui.profile.registerProfile.RegisterProfileActivity
import com.app.womba.utils.InterConsts
import com.app.womba.utils.getAppPref
import com.app.womba.utils.getUserData
import com.app.womba.utils.navigateClass

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashVM>(),
    BaseNavigator {

    override val viewModel: SplashVM
        get() = ViewModelProviders.of(this).get(SplashVM::class.java)
    override val layoutId: Int = R.layout.activity_splash
    override val bindingVariable: Int = BR.viewModel
    override val context: Context = this

    override fun onCreate() {
        mViewModel?.startTimer()
    }

    override fun initListeners() {
        mViewModel!!.mNavigator = this
    }

    override fun openActivity() {
        if (getAppPref().getString(InterConsts.USER_DATA, "").isNullOrEmpty()) {
            navigateClass<IntroActivity>()
        } else {
            if (getUserData().data!!.firstname!!.trim().isEmpty()) {
                navigateClass<RegisterProfileActivity>()
            } else {
                navigateClass<LandingActivity>()
            }
        }
        finish()
    }
}
