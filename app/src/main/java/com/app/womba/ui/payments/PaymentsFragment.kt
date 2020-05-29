package com.app.womba.ui.payments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.womba.R
import com.app.womba.adapter.PaymentsListingAdapter
import com.app.womba.model.PaymentListModel
import com.app.womba.utils.customViews.LoadingDialog
import com.app.womba.utils.responseHandler
import kotlinx.android.synthetic.main.payments_fragment.*
import java.util.ArrayList

class PaymentsFragment : Fragment() {

    companion object {
        fun newInstance() = PaymentsFragment()
    }

    private lateinit var viewModel: PaymentsViewModel
    lateinit var mAdapter: PaymentsListingAdapter

    var mPaymentArrayList=ArrayList<PaymentListModel.DataBean>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.payments_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PaymentsViewModel::class.java)
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
            Observer<PaymentListModel> { mData ->
                if (mData != null && activity!!.responseHandler(mData.code, mData.message)) {
                    mPaymentArrayList=mData.data!!
                    setAdapter()
                }
            })
        viewModel.getPaymentListing()

    }

    private fun setAdapter() {
        rvList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mAdapter = PaymentsListingAdapter(activity!!, mPaymentArrayList)
        rvList!!.adapter = mAdapter
        rvList.isNestedScrollingEnabled = false

        if (mPaymentArrayList.isEmpty()) {
            txtNoData.visibility = View.VISIBLE
        } else {
            txtNoData.visibility = View.GONE
        }
    }


}
