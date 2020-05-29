package com.app.womba.ui.doctorProfile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.app.womba.R
import com.app.womba.adapter.DoctorEducationAdapter
import com.app.womba.model.DoctorEducationResponse
import com.app.womba.model.DoctorListResponse
import com.app.womba.model.postResponseModel.DoctorEducationPost
import com.app.womba.utils.customViews.LoadingDialog
import com.app.womba.utils.responseHandler
import kotlinx.android.synthetic.main.activity_book_appointments.*
import kotlinx.android.synthetic.main.doctor_education_fragment.*

class DoctorEducationFragment : Fragment() {

    companion object {
        var mData: DoctorListResponse.DataBeanX.DataBean? = null

        fun newInstance(data: DoctorListResponse.DataBeanX.DataBean?):DoctorEducationFragment {
            mData=data
            return DoctorEducationFragment()
        }
    }

    private lateinit var viewModel: DoctorEducationViewModel
    lateinit var mAdapter: DoctorEducationAdapter

    var mEducationList= arrayListOf<DoctorEducationResponse.DataBean.EducationsBean>()

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.doctor_education_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DoctorEducationViewModel::class.java)
        // TODO: Use the ViewModel

        viewModel.educationList(DoctorEducationPost(mData!!.get_id()!!))

        viewModel.getLoader().observe(this,
            Observer { mData ->
                if (mData) {
                    LoadingDialog.getLoader().showLoader(activity!!)
                } else {
                    LoadingDialog.getLoader().dismissLoader()
                }
            })

        viewModel.getResponse().observe(this,
            Observer { mData ->
                if (mData != null && activity!!.responseHandler(mData.code, mData.message)) {
                    mEducationList=mData.data!!.educations!!
                    setAdapter()
                }
            })

    }

    fun setAdapter() {
        rvEducation.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mAdapter = DoctorEducationAdapter(activity!!,mEducationList)
        rvEducation!!.adapter = mAdapter
    }
}
