package com.app.womba.ui.appointments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.app.womba.R
import com.app.womba.adapter.ViewPagerAdapter
import com.app.womba.model.DoctorListResponse
import com.app.womba.ui.doctorProfile.DoctorEducationFragment
import com.app.womba.ui.doctorProfile.DoctorGeneralInfoFragment
import com.app.womba.ui.doctorProfile.SpecialityFragment
import com.app.womba.utils.InterConsts
import kotlinx.android.synthetic.main.activity_doctor_profile.*

class AppointmentsFragment : Fragment() {

    companion object {
        fun newInstance() = AppointmentsFragment()
    }

    private lateinit var viewModel: AppointmentsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.appointments_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AppointmentsViewModel::class.java)
        // TODO: Use the ViewModel
        setUpViewPager()
    }

    private fun setUpViewPager() {
        val adapter = ViewPagerAdapter(activity!!.supportFragmentManager)
        adapter.addFragment(UpcomingAppointmentsFragment.newInstance(), getString(R.string.upcoming_appointments))
        adapter.addFragment(PastAppointmentsFragment.newInstance(),getString(R.string.past_appointments))
        viewPager.adapter = adapter

        tabs.setupWithViewPager(viewPager)
    }

}
