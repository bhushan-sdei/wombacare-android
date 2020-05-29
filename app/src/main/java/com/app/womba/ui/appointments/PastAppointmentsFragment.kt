package com.app.womba.ui.appointments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.app.womba.R
import com.app.womba.adapter.PastAppointmentsAdapter
import com.app.womba.adapter.UpComingAppointmentListAdapter
import com.app.womba.model.AppointmentModel
import com.app.womba.utils.customViews.LoadingDialog
import com.app.womba.utils.responseHandler
import kotlinx.android.synthetic.main.past_appointments_fragment.*

class PastAppointmentsFragment : Fragment() {

    companion object {
        fun newInstance() = PastAppointmentsFragment()
    }

    private lateinit var mViewModel: PastAppointmentsViewModel
    var mAppointmentArrayList = arrayListOf<AppointmentModel.DataBean>()
    lateinit var mAdapter: PastAppointmentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.past_appointments_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(PastAppointmentsViewModel::class.java)
        // TODO: Use the ViewModel
        setAdapter()
        mViewModel.getLoader().observe(this,
            Observer { mData ->
                if (mData) {
                    LoadingDialog.getLoader().showLoader(activity!!)
                } else {
                    LoadingDialog.getLoader().dismissLoader()
                }
            })

        mViewModel.getResponse().observe(this,
            Observer<AppointmentModel> { mData ->
                if (mData != null && activity!!.responseHandler(mData.code, mData.message)) {
                    mAppointmentArrayList = mData.data!!
                    setAdapter()
                }
            })

        mViewModel.getPastAppointments()
    }

    private fun setAdapter() {
        rvAppointments.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mAdapter = PastAppointmentsAdapter(activity!!, mAppointmentArrayList)
        rvAppointments!!.adapter = mAdapter
        rvAppointments.isNestedScrollingEnabled = false

        if (mAppointmentArrayList.isEmpty()) {
            txtNoData.visibility = View.VISIBLE
        } else {
            txtNoData.visibility = View.GONE
        }
    }


}
