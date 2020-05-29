package com.app.womba.ui.appointments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.womba.R
import com.app.womba.adapter.UpComingAppointmentListAdapter
import com.app.womba.model.AppointmentModel
import com.app.womba.utils.responseHandler
import kotlinx.android.synthetic.main.upcoming_appointments_fragment.*


class UpcomingAppointmentsFragment : Fragment() {

    companion object {
        fun newInstance() = UpcomingAppointmentsFragment()
    }

    private lateinit var mViewModel: UpcomingAppointmentsViewModel
    var mAppointmentArrayList = arrayListOf<AppointmentModel.DataBean>()
    lateinit var mAdapter: UpComingAppointmentListAdapter
    lateinit var mLayoutManager: LinearLayoutManager
    private var loading = true
    private var offSet = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.upcoming_appointments_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(UpcomingAppointmentsViewModel::class.java)
        // TODO: Use the ViewModel
        setAdapter()
        mViewModel.getLoader().observe(this,
            Observer { mData ->
                loading = !mData
            })

        mViewModel.getResponse().observe(this,
            Observer<AppointmentModel> { mData ->
                if (mData != null && activity!!.responseHandler(mData.code, mData.message)) {
                    if (mData.data!!.isNotEmpty()) {
                        offSet++
                        mAppointmentArrayList.addAll(mData.data!!)
                    }
                    mAdapter.notifyAdapter(mAppointmentArrayList)

                    setNoDataVisibility()
                }
            })

        mViewModel.getUpcomingAppointments(offSet.toString())

        rvAppointments.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount: Int = mLayoutManager.childCount
                val totalItemCount: Int = mLayoutManager.itemCount
                val firstVisibleItemPosition: Int = mLayoutManager.findFirstVisibleItemPosition()
                if (loading) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                    ) {
                        mViewModel.getUpcomingAppointments(offSet.toString())
                    }
                }
            }
        })

    }

    private fun setAdapter() {
        mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvAppointments.layoutManager = mLayoutManager
        mAdapter = UpComingAppointmentListAdapter(activity!!, mAppointmentArrayList)
        rvAppointments!!.adapter = mAdapter
        rvAppointments.isNestedScrollingEnabled = false
        setNoDataVisibility()
    }

    private fun setNoDataVisibility() {
        if (mAppointmentArrayList.isEmpty()) {
            txtNoData.visibility = View.VISIBLE
        } else {
            txtNoData.visibility = View.GONE
        }
    }

}
