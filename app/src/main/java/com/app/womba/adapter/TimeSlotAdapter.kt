package com.app.womba.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.womba.R
import com.app.womba.model.DoctorSlotModel
import com.app.womba.utils.getTime
import com.app.womba.utils.getTimeSlots
import kotlinx.android.synthetic.main.item_doctor_time_slot.view.*

class TimeSlotAdapter(
    var context: Context,
    var mData: ArrayList<DoctorSlotModel.DataBean>
) : RecyclerView.Adapter<TimeSlotAdapter.BindingHolder>() {


    var mSelectedSlotArrayList = ArrayList<DoctorSlotModel.DataBean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_doctor_time_slot, parent, false)
        return BindingHolder(view)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.btnTimeSlot.setText(getTimeSlots(mData[position].start))


        if (mData[position].selected) {
            holder.btnTimeSlot.background =
                context.resources.getDrawable(R.drawable.slot_selected)
        } else {
            holder.btnTimeSlot.background =
                context.resources.getDrawable(R.drawable.slot_available)
        }

        holder.btnTimeSlot.setOnClickListener {
            for (i in mData.indices) {
                mData[i].selected = false
            }
            mSelectedSlotArrayList = ArrayList<DoctorSlotModel.DataBean>()

            if (mData[position].selected) {
                mData[position].selected = false
                mSelectedSlotArrayList.remove(mData[position])
            } else {
                mData[position].selected = true
                mSelectedSlotArrayList.add(mData[position])
            }


            notifyDataSetChanged()
        }

    }

    fun getSelectedSlots(): ArrayList<DoctorSlotModel.DataBean> {
        return mSelectedSlotArrayList
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class BindingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var btnTimeSlot = itemView.btnTimeSlot
    }

}