package com.app.womba.ui.doctorProfile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.app.womba.R
import com.app.womba.model.DoctorListResponse
import com.app.womba.network.WS_BASE_DOCTOR_IMAGE_URL
import com.app.womba.utils.InterConsts
import com.app.womba.utils.loadUserPhoto
import com.app.womba.utils.textChecker
import kotlinx.android.synthetic.main.doctor_general_info_fragment.*

class DoctorGeneralInfoFragment : Fragment() {

    companion object {
        var mData: DoctorListResponse.DataBeanX.DataBean? = null

        fun newInstance(data: DoctorListResponse.DataBeanX.DataBean?): DoctorGeneralInfoFragment {
            mData = data
            return DoctorGeneralInfoFragment()
        }
    }

    private lateinit var viewModel: DoctorGeneralInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.doctor_general_info_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DoctorGeneralInfoViewModel::class.java)
        // TODO: Use the ViewModel
        setData()

        btnAppointment.setOnClickListener {
            val intent = Intent(activity!!, DoctorTimeSlotActivity::class.java)
            intent.putExtra(InterConsts.EXTRA, mData)
            startActivity(intent)

        }

    }

    @SuppressLint("SetTextI18n")
    fun setData() {
        textView_name.text = "Dr. " +
                textChecker(mData!!.firstname!!) + " " + textChecker(mData!!.lastname!!)
        textView_address.text = textChecker(mData!!.address!!)
        txtEmail.text = textChecker(mData!!.email!!)
        txtNpiNumber.text = textChecker("--")
        txtCity.text = textChecker(mData!!.city!!)
        txtState.text = textChecker(mData!!.state!!)
        txtCountry.text = textChecker(mData!!.country!!)
        txtZipCode.text = textChecker(mData!!.zip!!)
        txtAddress.text = textChecker(mData!!.address!!)

        if (mData!!.gender == InterConsts.FEMALE) {
            imgGender.setImageDrawable(resources.getDrawable(R.drawable.ic_gender))
        } else {
            imgGender.setImageDrawable(resources.getDrawable(R.drawable.ic_male))
        }

        imageView_doctor_profile.loadUserPhoto(WS_BASE_DOCTOR_IMAGE_URL + mData!!.profile_pic)
    }

}
