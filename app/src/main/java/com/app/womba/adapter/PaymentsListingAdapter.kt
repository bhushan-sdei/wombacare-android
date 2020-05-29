package com.app.womba.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.womba.R
import com.app.womba.model.PaymentListModel
import com.app.womba.utils.convertUTCToLocalDate
import kotlinx.android.synthetic.main.item_past_payments.view.*

class PaymentsListingAdapter(
    var context: Context,
    var mData: ArrayList<PaymentListModel.DataBean>
) : RecyclerView.Adapter<PaymentsListingAdapter.BindingHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingHolder {
        val view =  LayoutInflater.from(context).inflate(R.layout.item_past_payments, parent, false)
        return BindingHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: BindingHolder,
        position: Int) {

        holder.textView_doctor_name.setText(mData[position].doctorName)
        holder.textView_amount.setText(context.resources.getString(R.string.dollar_sign)+mData[position].ammount)
        holder.txtPaymentTime.setText(convertUTCToLocalDate(mData[position].appointmentDate))
        holder.textView_date.setText(mData[position].appointmentDate)
        holder.textView_time.setText(mData[position].appointmentTime)

    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class BindingHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var textView_amount=itemView.textView_amount
        var textView_doctor_name=itemView.textView_doctor_name
        var textView_date=itemView.textView_date
        var textView_time=itemView.textView_time
        var txtPaymentTime=itemView.txtPaymentTime

    }

}