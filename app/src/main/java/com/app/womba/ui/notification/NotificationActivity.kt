package com.app.womba.ui.notification

import android.content.Context
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.womba.BR
import com.app.womba.R
import com.app.womba.adapter.NotificationListAdapter
import com.app.womba.base.BaseActivity
import com.app.womba.databinding.ActivityNotificationBinding
import com.app.womba.model.NotificationListModel
import com.app.womba.utils.customViews.LoadingDialog
import com.app.womba.utils.responseHandler
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.payments_fragment.*
import kotlinx.android.synthetic.main.payments_fragment.rvList
import kotlinx.android.synthetic.main.payments_fragment.txtNoData

class NotificationActivity : BaseActivity<ActivityNotificationBinding, NotificationVM>() {

    override val viewModel: NotificationVM
        get() = ViewModelProviders.of(this).get(NotificationVM::class.java)
    override val layoutId: Int = R.layout.activity_notification
    override val bindingVariable: Int = BR.viewModel
    override val context: Context = this

    var mList = arrayListOf<NotificationListModel.DataBean>()

    override fun onCreate() {
    }

    override fun initListeners() {
        imgBack.setOnClickListener {
            finish()
        }

        mViewModel!!.getLoader().observe(this,
            Observer { mData ->
                if (mData) {
                    LoadingDialog.getLoader().showLoader(this)
                } else {
                    LoadingDialog.getLoader().dismissLoader()
                }
            })

        mViewModel!!.getResponse().observe(this,
            Observer<NotificationListModel> { mData ->
                if (mData != null && responseHandler(mData.code, mData.message)) {
                    mList = mData.data!!
                    setAdapter()
                }
            })
        mViewModel!!.getNotificationList()

    }

    lateinit var mAdapter: NotificationListAdapter
    private fun setAdapter() {
        rvList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mAdapter = NotificationListAdapter(this, mList)
        rvList!!.adapter = mAdapter
        rvList.isNestedScrollingEnabled = false

        if (mList.isEmpty()) {
            txtNoData.visibility = View.VISIBLE
        } else {
            txtNoData.visibility = View.GONE
        }
    }


}
