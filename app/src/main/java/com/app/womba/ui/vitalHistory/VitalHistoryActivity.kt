package com.app.womba.ui.vitalHistory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.womba.R
import com.app.womba.adapter.ViewPagerAdapter
import com.app.womba.ui.appointments.PastAppointmentsFragment
import com.app.womba.ui.appointments.UpcomingAppointmentsFragment
import kotlinx.android.synthetic.main.activity_vital_history.*

class VitalHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vital_history)
        setUpViewPager()

        imgBack.setOnClickListener {
            finish()
        }

    }

    private fun setUpViewPager() {
        val adapter = ViewPagerAdapter(this.supportFragmentManager)
        adapter.addFragment(
            VitalTrendsFragment.newInstance(),
            resources.getString(R.string.trends)
        )
        adapter.addFragment(
            VitalLogFragment.newInstance(),
            resources.getString(R.string.logs)
        )
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }


}
