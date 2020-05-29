package com.app.womba.ui.vitalHistory

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.womba.R
import com.app.womba.adapter.vitalhistoryadapter.ContactsSection
import com.app.womba.model.VitalHistoryData
import com.app.womba.model.fullreport.HealthModel
import com.app.womba.model.postResponseModel.ScanHistoryPostModel
import com.app.womba.ui.binah.VitalDetailActivity
import com.app.womba.utils.*
import com.app.womba.utils.customViews.LoadingDialog
import com.app.womba.utils.pagination.EndlessRecyclerViewScrollListener
import com.google.gson.Gson
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import kotlinx.android.synthetic.main.payments_fragment.rvList
import kotlinx.android.synthetic.main.vital_log_fragment.*

class VitalLogFragment : Fragment(), ContactsSection.ClickListener {

    companion object {
        fun newInstance() = VitalLogFragment()
    }

    private lateinit var viewModel: VitalLogViewModel

    private var searchScrollListener: EndlessRecyclerViewScrollListener? = null
    private var pageNumber: Int = 1

    private var sectionedAdapter: SectionedRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.vital_log_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(VitalLogViewModel::class.java)
        // TODO: Use the ViewModel

        viewModel.getLoader().observe(this,
            Observer { mData ->
                if (mData) {
                    LoadingDialog.getLoader().showLoader(activity!!)
                } else {
                    LoadingDialog.getLoader().dismissLoader()
                }
            })

        viewModel.getResponse().observe(this,
            Observer<VitalHistoryData> { mData ->
                if (mData != null && activity!!.responseHandlerNoToast(mData.code, mData.message)) {
                    addItemstoList(mData)
                }
            })

        viewModel.getEvitalsHistorydata(
            ScanHistoryPostModel(
                getUserData().data!!.get_id()!!,
                pageNumber, 5
            )
        )
        swipeRefresh.setOnRefreshListener {
            resetListState()
            viewModel.getEvitalsHistorydata(
                ScanHistoryPostModel(
                    getUserData().data!!.get_id()!!,
                    pageNumber, 5
                )
            )
            swipeRefresh.isRefreshing = false
        }

        setUpAdapter()
    }

    private fun resetListState() {
        sectionedAdapter?.removeAllSections()
        sectionedAdapter?.notifyDataSetChanged()
        searchScrollListener?.resetState()
        pageNumber = 1
    }


    private fun addItemstoList(list: VitalHistoryData) {
        for (i in list.data!!.bindata!!.indices) {
            sectionedAdapter?.addSection(list.data!!.bindata!![i].reportDate?.let { it1 ->
                list.data!!.bindata!![i].scanDataList?.let { it2 ->
                    ContactsSection(
                        activity!!.applicationContext,
                        it1,
                        it2,
                        this
                    )
                }
            })
            sectionedAdapter?.itemCount?.let { it1 ->
                sectionedAdapter?.notifyItemRangeInserted(
                    it1,
                    list.data!!.bindata!!.size
                )
            }
        }
    }

    /** Method used for Set Up Adapter for the List displayed on the screen*/
    private fun setUpAdapter() {
        sectionedAdapter = SectionedRecyclerViewAdapter()
        val layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        rvList.layoutManager = layoutManager
        rvList.itemAnimator = DefaultItemAnimator()
        rvList.adapter = sectionedAdapter

        // Retain an instance so that you can call `resetState()` for fresh searches
        searchScrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                /** Triggered only when new data needs to be appended to the list
                Add whatever code is needed to append new items to the bottom of the list*/
                viewModel.getEvitalsHistorydata(
                    ScanHistoryPostModel(
                        getUserData().data!!.get_id()!!,
                        pageNumber.plus(page), 5
                    )
                )
            }
        }
        rvList.addOnScrollListener(searchScrollListener as EndlessRecyclerViewScrollListener)
    }

    private fun openResults(data: String) {
        val openResultIntent = Intent(activity, VitalDetailActivity::class.java)
        openResultIntent.putExtra(InterConsts.HEALTH_REPORT, data)
        startActivity(openResultIntent)
    }

    override fun onItemClick(view: View, position: Int, data: VitalHistoryData.DataBean.DataX) {

        var model=HealthModel()

        model.setReport(data.report)
        model.setReportNew(data.report)
        model.setScanReport(data.scanReport)
        model.setPatientId(getUserData().data!!.get_id())

        data.scanReport?.let {
            openResults(Gson().toJson(model))
        }
    }



}
