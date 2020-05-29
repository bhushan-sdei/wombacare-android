package com.app.womba.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.womba.R
import com.app.womba.model.DoctorListResponse
import kotlinx.android.synthetic.main.item_doctor_speciality.view.*

class DoctorSpecialityAdapter(
    var context: Context,
    var mData: ArrayList<DoctorListResponse.DataBeanX.DataBean.SpecialityInfoBean>
) : RecyclerView.Adapter<DoctorSpecialityAdapter.BindingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_doctor_speciality, parent, false)
        return BindingHolder(view)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.lblSpeciality.text = mData[position].name
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class BindingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var lblSpeciality = itemView.lblSpeciality
    }

}