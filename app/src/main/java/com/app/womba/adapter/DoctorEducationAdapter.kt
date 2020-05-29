package com.app.womba.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.womba.R
import com.app.womba.model.DoctorEducationResponse
import com.app.womba.utils.convertUTCToLocalDate
import kotlinx.android.synthetic.main.item_doctor_education.view.*

class DoctorEducationAdapter(var context: Context,
    var mData: ArrayList<DoctorEducationResponse.DataBean.EducationsBean>) : RecyclerView.Adapter<DoctorEducationAdapter.BindingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_doctor_education, parent, false)
        return BindingHolder(view)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.textView_college.text = mData[position].college
        holder.textView_degree.text = mData[position].degree
        holder.textView_year.text = convertUTCToLocalDate(mData[position].year.toString())
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class BindingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView_degree = itemView.textView_degree
        var textView_college = itemView.textView_college
        var textView_year = itemView.textView_year
    }

}