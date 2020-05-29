package com.app.womba.ui.doctorProfile

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.app.womba.BR
import com.app.womba.R
import com.app.womba.adapter.ViewPagerAdapter
import com.app.womba.base.BaseActivity
import com.app.womba.databinding.ActivityDoctorProfileBinding
import com.app.womba.model.DoctorListResponse
import com.app.womba.utils.InterConsts
import kotlinx.android.synthetic.main.activity_doctor_profile.*

class DoctorProfileActivity : BaseActivity<ActivityDoctorProfileBinding, DoctorProfileVM>() {

    override val viewModel: DoctorProfileVM
        get() = ViewModelProviders.of(this).get(DoctorProfileVM::class.java)
    override val layoutId: Int = R.layout.activity_doctor_profile
    override val bindingVariable: Int = BR.viewModel
    override val context: Context = this

    override fun onCreate() {
        setUpViewPager()
        imgBack.setOnClickListener {
            finish()
        }

    }

    override fun initListeners() {

    }

    private fun setUpViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(DoctorGeneralInfoFragment.newInstance(intent.getParcelableExtra<DoctorListResponse.DataBeanX.DataBean>(InterConsts.EXTRA)), getString(R.string.general_information))
        adapter.addFragment(DoctorEducationFragment.newInstance(intent.getParcelableExtra<DoctorListResponse.DataBeanX.DataBean>(InterConsts.EXTRA)),getString(R.string.education))
        adapter.addFragment(SpecialityFragment.newInstance(intent.getParcelableExtra<DoctorListResponse.DataBeanX.DataBean>(InterConsts.EXTRA)),getString(R.string.speciality))
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

}
