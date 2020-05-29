package com.app.womba.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.womba.R
import com.app.womba.adapter.UpComingAppointmentListAdapter
import com.app.womba.model.AppointmentModel
import com.app.womba.ui.bookAppointment.BookAppointmentActivity
import com.app.womba.ui.notification.NotificationActivity
import com.app.womba.ui.vitalHistory.VitalHistoryActivity
import com.app.womba.utils.customViews.LoadingDialog
import com.app.womba.utils.navigateClass
import com.app.womba.utils.responseHandler
import kotlinx.android.synthetic.main.dashboard_fragment.*

class DashboardFragment : Fragment() {

    companion object {
        fun newInstance() = DashboardFragment()
    }

    private lateinit var mViewModel: DashboardViewModel
    lateinit var mAdapter: UpComingAppointmentListAdapter

    var mAppointmentArrayList = arrayListOf<AppointmentModel.DataBean>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dashboard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
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
        mViewModel.getUpcomingAppointments()

        txtBookAppointment.setOnClickListener {
            activity!!.navigateClass<BookAppointmentActivity>()
        }

        imgNotification.setOnClickListener {
            activity!!.navigateClass<NotificationActivity>()
        }

        llMedicalHistory.setOnClickListener {
            activity!!.navigateClass<VitalHistoryActivity>()
        }

    }

    private fun setAdapter() {
        rvAppointments.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mAdapter = UpComingAppointmentListAdapter(activity!!, mAppointmentArrayList)
        rvAppointments!!.adapter = mAdapter
        rvAppointments.isNestedScrollingEnabled = false

        if (mAppointmentArrayList.isEmpty()) {
            txtNoData.visibility = View.VISIBLE
        } else {
            txtNoData.visibility = View.GONE
        }
    }

}
