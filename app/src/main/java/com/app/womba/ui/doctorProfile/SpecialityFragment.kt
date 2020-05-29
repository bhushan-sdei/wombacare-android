package com.app.womba.ui.doctorProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.womba.R
import com.app.womba.adapter.DoctorSpecialityAdapter
import com.app.womba.model.DoctorListResponse
import com.app.womba.model.postResponseModel.DoctorSpecialityPost
import com.app.womba.utils.customViews.LoadingDialog
import kotlinx.android.synthetic.main.speciality_fragment.*

class SpecialityFragment : Fragment() {

    companion object {
        var mData: DoctorListResponse.DataBeanX.DataBean? = null

        fun newInstance(data: DoctorListResponse.DataBeanX.DataBean?): SpecialityFragment {
            mData = data
            return SpecialityFragment()
        }
    }

    private lateinit var viewModel: SpecialityViewModel

    lateinit var mAdapter: DoctorSpecialityAdapter
    var mSpecialityList = arrayListOf<DoctorListResponse.DataBeanX.DataBean.SpecialityInfoBean>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.speciality_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SpecialityViewModel::class.java)
        // TODO: Use the ViewModel

        viewModel.getLoader().observe(this,
            Observer { mData ->
                if (mData) {
                    LoadingDialog.getLoader().showLoader(activity!!)
                } else {
                    LoadingDialog.getLoader().dismissLoader()
                }
            })

        mSpecialityList = mData!!.specialityInfo!!
        setAdapter()
    }

    fun setAdapter() {
        rvSpeciality.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mAdapter = DoctorSpecialityAdapter(activity!!, mSpecialityList)
        rvSpeciality!!.adapter = mAdapter
    }
}
