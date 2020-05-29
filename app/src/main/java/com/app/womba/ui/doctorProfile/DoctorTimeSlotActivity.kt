package com.app.womba.ui.doctorProfile

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.app.womba.BR
import com.app.womba.R
import com.app.womba.adapter.TimeSlotAdapter
import com.app.womba.base.BaseActivity
import com.app.womba.databinding.ActivityDoctorTimeSlotBinding
import com.app.womba.model.DoctorListResponse
import com.app.womba.model.DoctorSlotModel
import com.app.womba.model.postResponseModel.DoctorSlotTimePost
import com.app.womba.ui.payment.PaymentActivity
import com.app.womba.utils.*
import com.app.womba.utils.customViews.LoadingDialog
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_doctor_time_slot.*
import java.util.*
import kotlin.collections.ArrayList

class DoctorTimeSlotActivity : BaseActivity<ActivityDoctorTimeSlotBinding, DoctorTimeSlotVM>() {

    override val viewModel: DoctorTimeSlotVM
        get() = ViewModelProviders.of(this).get(DoctorTimeSlotVM::class.java)
    override val layoutId: Int = R.layout.activity_doctor_time_slot
    override val bindingVariable: Int = BR.viewModel
    override val context: Context = this
    var utcDate: String = ""
    lateinit var mData: DoctorListResponse.DataBeanX.DataBean
    var mSlotArrayList = ArrayList<DoctorSlotModel.DataBean>()



    override fun onCreate() {
        mData =
            intent.getParcelableExtra<DoctorListResponse.DataBeanX.DataBean>(InterConsts.EXTRA)!!

        setCalendar()

    }

    private fun setCalendar() {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        val thisYear = calendar[Calendar.YEAR]
        val thisMonth = calendar[Calendar.MONTH]
        val thisDay = calendar[Calendar.DAY_OF_MONTH]
        calendar[thisYear, thisMonth] = thisDay
        utcDate = convertLocaleDateToUTC(getFromattedCurrentDate()!!, this)!!
        try {
            calendarView.setDate(calendar)
        } catch (e: OutOfDateRangeException) {
            e.printStackTrace()
        }
        calendarView.setMinimumDate(calendar)

    }

    override fun initListeners() {

        mViewModel!!.getLoader().observe(this,
            androidx.lifecycle.Observer { mData ->
                if (mData) {
                    LoadingDialog.getLoader().showLoader(this)
                } else {
                    LoadingDialog.getLoader().dismissLoader()
                }
            })

        mViewModel!!.getResponse().observe(this,
            androidx.lifecycle.Observer<DoctorSlotModel> { mData ->
                if (mData != null && responseHandler(mData.code, mData.message)) {
                    mSlotArrayList = mData.getData()!!
                    setAdapter()
                }
            })

//        2020-05-14T00:00:00.000Z
//        2020-04-30T09:00:00+05:30
//        var timeCoverted=utcDate.replace(".000Z","+05:30")

        mViewModel!!.getDoctorSlotByDate(
            DoctorSlotTimePost(
                mData.get_id()!!,
                utcDate,
                getTimeZone()!!
            )
        )

        calendarView.setOnDayClickListener { eventDay ->
            utcDate = convertLocaleDateToUTC(
                eventDay.calendar.time.toString(),
                "EEE MMM dd hh:mm:ss z yyyy",
                this
            )!!

            //        2020-05-14T00:00:00.000Z
//        2020-04-30T09:00:00+05:30
//            var timeCoverted=utcDate.replace(".000Z","+05:30")


            mViewModel!!.getDoctorSlotByDate(
                DoctorSlotTimePost(
                    mData.get_id()!!,
                    utcDate,
                    getTimeZone()!!
                )
            )
        }

        imgBack.setOnClickListener {
            finish()
        }

        btnNext.setOnClickListener {
            if(mAdapter.getSelectedSlots().isNotEmpty()){
                showDailogReason()
            }else{
                showOKDialog(getString(R.string.error_select_time_slot))
            }
        }
    }

    lateinit var mAdapter: TimeSlotAdapter

    private fun setAdapter() {
        recyclerView.layoutManager = GridLayoutManager(this, 4)
        mAdapter = TimeSlotAdapter(this, mSlotArrayList)
        recyclerView!!.adapter = mAdapter

        if (mSlotArrayList.isEmpty()) {
            txtNoData.visibility = View.VISIBLE
        } else {
            txtNoData.visibility = View.GONE
        }
    }

    private fun showDailogReason() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_appointment_reason)

        val lp = WindowManager.LayoutParams()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        lp.copyFrom(dialog.window!!.attributes)
        dialog.window!!.setGravity(Gravity.CENTER)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window!!.attributes = lp

        val inputLayout =
            dialog.findViewById<View>(R.id.layoutReason) as TextInputLayout
        val inputText =
            dialog.findViewById<View>(R.id.inputReason) as TextInputEditText
        val btnSubmit =
            dialog.findViewById<View>(R.id.button_chat) as Button

        btnSubmit.setOnClickListener {
            if (TextUtils.isEmpty(inputText.text.toString())) {
                inputLayout.error = "Reason is mandatory"
            } else {
                val intent=Intent(mContext,PaymentActivity::class.java)
                intent.putExtra("startTime", mAdapter.getSelectedSlots()[0].start)
                intent.putExtra("endTime",mAdapter.getSelectedSlots()[0].end)
                intent.putExtra("checkslot",mAdapter.getSelectedSlots()[0].checkslot)
                intent.putExtra("appointmentDate",utcDate)
                intent.putExtra("patientId", getUserData().data!!.get_id())
                intent.putExtra("doctorId",mData.get_id())
                intent.putExtra("userTimezone", getTimeZone())
                intent.putExtra("reason",inputText.text.toString())
                intent.putExtra("fee", mData.fee)
                startActivity(intent)
            }
        }

        dialog.show()
    }


}
