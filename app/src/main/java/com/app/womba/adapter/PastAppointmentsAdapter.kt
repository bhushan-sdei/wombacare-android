package com.app.womba.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.womba.R
import com.app.womba.model.AppointmentModel
import com.app.womba.network.WS_BASE_DOCTOR_IMAGE_URL
import com.app.womba.ui.appointments.AppointmentDetailActivity
import com.app.womba.utils.InterConsts
import com.app.womba.utils.convertUTCToLocalDate
import com.app.womba.utils.getTime
import com.app.womba.utils.loadUserPhoto
import kotlinx.android.synthetic.main.item_past_appointments.view.*
import kotlinx.android.synthetic.main.item_upcomingappoitments.view.imageView_doctor_profile
import kotlinx.android.synthetic.main.item_upcomingappoitments.view.textView_address
import kotlinx.android.synthetic.main.item_upcomingappoitments.view.textView_date
import kotlinx.android.synthetic.main.item_upcomingappoitments.view.textView_doctor_name
import kotlinx.android.synthetic.main.item_upcomingappoitments.view.textView_time

class PastAppointmentsAdapter(
    var context: Context,
    var mData: ArrayList<AppointmentModel.DataBean>
) : RecyclerView.Adapter<PastAppointmentsAdapter.BindingHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingHolder {
        val view =  LayoutInflater.from(context).inflate(R.layout.item_past_appointments, parent, false)
        return BindingHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: BindingHolder,
        position: Int) {

        holder.textView_doctor_name.setText(mData[position].doctorInfo!!.firstname + " " + mData[position].doctorInfo!!.lastname)
        holder.textView_addresss.setText(mData[position].doctorInfo!!.address)
        holder.textView_date.setText(mData[position].weekDay + ", " + convertUTCToLocalDate(mData[position].appointmentDate))
        holder.textView_time.setText(
            getTime(mData[position].finalAppointmentStartwithClincTimezone) + " - " +
                    getTime(mData[position].finalAppointmentEndwithClincTimezone)
        )
        holder.imageView_doctor_profile.loadUserPhoto(WS_BASE_DOCTOR_IMAGE_URL +mData[position].doctorInfo!!.profile_pic)


        holder.appointment_detail.setOnClickListener {
            var intent= Intent(context, AppointmentDetailActivity::class.java)
            intent.putExtra(InterConsts.EXTRA,mData[position])
            intent.putExtra(InterConsts.DOCTOR_INFO,mData[position].doctorInfo)
            intent.putExtra(InterConsts.FROM,InterConsts.PAST)
            if(mData[position].patientconsultedformsData!=null) {
                intent.putExtra(InterConsts.PRESCRIPTION, mData[position].patientconsultedformsData)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class BindingHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var textView_doctor_name = itemView.textView_doctor_name
        var textView_addresss = itemView.textView_address
        var textView_date = itemView.textView_date
        var textView_time = itemView.textView_time
        var appointment_detail = itemView.appointment_detail
        var imageView_doctor_profile = itemView.imageView_doctor_profile
    }

}