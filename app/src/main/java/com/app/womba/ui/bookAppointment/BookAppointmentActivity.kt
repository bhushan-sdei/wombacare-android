package com.app.womba.ui.bookAppointment

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.womba.BR
import com.app.womba.R
import com.app.womba.adapter.DoctorListAdapter
import com.app.womba.base.BaseActivity
import com.app.womba.databinding.ActivityBookAppointmentsBinding
import com.app.womba.model.DoctorListResponse
import com.app.womba.model.postResponseModel.ListPostResponse
import com.app.womba.ui.doctorProfile.DoctorProfileActivity
import com.app.womba.utils.InterConsts
import com.app.womba.utils.customViews.LoadingDialog
import com.app.womba.utils.responseHandler
import kotlinx.android.synthetic.main.activity_book_appointments.*


class BookAppointmentActivity : BaseActivity<ActivityBookAppointmentsBinding, BookAppointmentsViewModel>(),
    DoctorListAdapter.ItemClick {

    override val viewModel: BookAppointmentsViewModel
        get() = ViewModelProviders.of(this).get(BookAppointmentsViewModel::class.java)
    override val layoutId: Int = R.layout.activity_book_appointments
    override val bindingVariable: Int = BR.viewModel
    override val context: Context = this

    lateinit var mAdapter: DoctorListAdapter
    var mDoctorArrayList = arrayListOf<DoctorListResponse.DataBeanX.DataBean>()

    fun setAdapter() {
        rvDoctors.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mAdapter = DoctorListAdapter(this, mDoctorArrayList, this)
        rvDoctors!!.adapter = mAdapter


        if(mDoctorArrayList.isEmpty()){
            txtNoData.visibility=View.VISIBLE
        }else{
            txtNoData.visibility=View.GONE
        }
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, DoctorProfileActivity::class.java)
        intent.putExtra(InterConsts.EXTRA,mDoctorArrayList[position])
        startActivity(intent)
    }

    override fun onCreate() {
        mViewModel!!.getAllDoctorsList(ListPostResponse("10","2",""))
    }

    override fun initListeners() {
        LoadingDialog.getLoader().showLoader(this)
        mViewModel!!.getLoader().observe(this,
            Observer { mData ->
                if (!mData) {
                    LoadingDialog.getLoader().dismissLoader()
                }else{
                    LoadingDialog.getLoader().showLoader(this)
                }
            })

        mViewModel!!.getResponse().observe(this,
            Observer { mData ->
                if (mData != null && responseHandler(mData.code, mData.message)) {
                    mDoctorArrayList=mData.data!!.data!!
                    txtTotalCount.text=mData.data!!.totalCount.toString()+" Doctors are available for consultation"
                    setAdapter()
                }
            })

        txtSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard()
                mViewModel!!.getAllDoctorsList(ListPostResponse("10","1",txtSearch.text.toString()))
                return@OnEditorActionListener true
            }
            false
        })

        txtSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence,start: Int,before: Int,count: Int ) {
                if(txtSearch.text!!.trim().toString().isEmpty()) {
                    txtSearch.setFocusable(false)
                    mViewModel!!.getAllDoctorsList(
                        ListPostResponse(
                            "10",
                            "1",
                            ""
                        )
                    )
                }
            }

            override fun beforeTextChanged(s: CharSequence,start: Int, count: Int,  after: Int) {

            }

            override fun afterTextChanged(s: Editable) {

            }
        })

        imgBack.setOnClickListener {
            finish()
        }
    }
}
