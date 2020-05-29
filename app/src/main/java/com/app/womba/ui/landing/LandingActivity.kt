package com.app.womba.ui.landing

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.app.womba.BR
import com.app.womba.R
import com.app.womba.adapter.ViewPagerAdapter
import com.app.womba.base.BaseActivity
import com.app.womba.databinding.ActivityLandingBinding
import com.app.womba.ui.appointments.AppointmentsFragment
import com.app.womba.ui.dashboard.DashboardFragment
import com.app.womba.ui.payments.PaymentsFragment
import com.app.womba.ui.profile.ProfileFragment
import com.app.womba.utils.InterConsts
import kotlinx.android.synthetic.main.activity_landing.*
import kotlinx.android.synthetic.main.bottom_layout.*

class LandingActivity : BaseActivity<ActivityLandingBinding, LandingVM>(), View.OnClickListener {

    override val bindingVariable: Int
    get() = BR.viewModel

    override val layoutId: Int
    get() = R.layout.activity_landing

    override val viewModel: LandingVM
    get() = ViewModelProviders.of(this).get(LandingVM::class.java)

    override val context: Context
    get() = this

    var mFragAdapter = ViewPagerAdapter(supportFragmentManager)
    var mViewSelection = InterConsts.FRAG_NULL

    override fun onCreate() {
        mFragAdapter = ViewPagerAdapter(supportFragmentManager)
        mFragAdapter.addFragment(DashboardFragment.newInstance())
        mFragAdapter.addFragment(AppointmentsFragment.newInstance())
        mFragAdapter.addFragment(PaymentsFragment.newInstance())
        mFragAdapter.addFragment(ProfileFragment.newInstance())
        vpFrag.setPagingEnabled(false)
        vpFrag.adapter = mFragAdapter
        vpFrag.currentItem = InterConsts.FRAG_HOME
        vpFrag.offscreenPageLimit = 4

        loadFragment(InterConsts.FRAG_HOME)
    }

    override fun initListeners() {
        ll_profile.setOnClickListener(this)
        ll_cards.setOnClickListener(this)
        ll_messages.setOnClickListener(this)
        ll_bookings.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ll_profile -> {
                loadFragment(InterConsts.FRAG_HOME)
            }
            R.id.ll_bookings -> {
                loadFragment(InterConsts.FRAG_APPOINTMENTS)
            }
            R.id.ll_cards -> {
                loadFragment(InterConsts.FRAG_PAYMENTS)
            }
            R.id.ll_messages -> {
                loadFragment(InterConsts.FRAG_PROFILE)
            }
        }
    }

    private fun loadFragment(selected: Int) {
        hideKeyboard()
        imgHome.setImageResource(R.drawable.ic_home)
        imgBookings.setImageResource(R.drawable.ic_appointments)
        imgCoins.setImageResource(R.drawable.ic_payment)
        imgMessage.setImageResource(R.drawable.ic_profile)

        txtHome.setTextColor(resources.getColor(R.color.black))
        txtBookings.setTextColor(resources.getColor(R.color.black))
        txtCoins.setTextColor(resources.getColor(R.color.black))
        txtMessage.setTextColor(resources.getColor(R.color.black))

        when (selected) {
            InterConsts.FRAG_HOME -> {
                vpFrag.currentItem = InterConsts.FRAG_HOME
                imgHome.setImageResource(R.drawable.ic_home_s)
                txtHome.setTextColor(resources.getColor(R.color.colorPrimaryDark))
            }

            InterConsts.FRAG_APPOINTMENTS -> {
                vpFrag.currentItem = InterConsts.FRAG_APPOINTMENTS
                imgBookings.setImageResource(R.drawable.ic_appointments_s)
                txtBookings.setTextColor(resources.getColor(R.color.colorPrimaryDark))
            }

            InterConsts.FRAG_PAYMENTS -> {
                vpFrag.currentItem = InterConsts.FRAG_PAYMENTS
                imgCoins.setImageResource(R.drawable.ic_payment_s)
                txtCoins.setTextColor(resources.getColor(R.color.colorPrimaryDark))
            }

            InterConsts.FRAG_PROFILE -> {
                vpFrag.currentItem = InterConsts.FRAG_PROFILE
                imgMessage.setImageResource(R.drawable.ic_profile_s)
                txtMessage.setTextColor(resources.getColor(R.color.colorPrimaryDark))
            }
        }

        if (mViewSelection != selected) {
            mViewSelection = selected
        }
    }
}
