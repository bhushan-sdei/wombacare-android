package com.app.womba.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.womba.R
import com.app.womba.model.DoctorListResponse
import com.app.womba.network.WS_BASE_DOCTOR_IMAGE_URL
import com.app.womba.utils.loadUserPhoto
import kotlinx.android.synthetic.main.item_doctor.view.*

class DoctorListAdapter(
    var context: Context,
    var mData: ArrayList<DoctorListResponse.DataBeanX.DataBean>,
    var mClick: ItemClick
) : RecyclerView.Adapter<DoctorListAdapter.BindingHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_doctor, parent, false)
        return BindingHolder(view)
    }

    override fun onBindViewHolder(
        holder: BindingHolder,
        position: Int
    ) {
        holder.textView_doctor_name.text =
            mData[position].firstname + " " + mData[position].lastname
        holder.textView_addresss.text = mData[position].address

        var appointtype = ""

        for (model in mData[position].specialityInfo!!) {
            appointtype = appointtype + "," + model.name.toString()
        }
        if (appointtype.isNotEmpty()) {
            holder.appointment_type.text = appointtype.substring(1, appointtype.length).trim()
        }

        holder.txtViewMore.setOnClickListener {
            mClick.onItemClick(position)
        }
        holder.imageView_doctor_profile.loadUserPhoto(WS_BASE_DOCTOR_IMAGE_URL + mData[position].profile_pic)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class BindingHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var imgDoctorProfile: ImageView
        var textView_doctor_name: TextView
        var textView_addresss: TextView
        var appointment_type: TextView
        var txtViewMore = itemView.txtViewMore
        var imageView_doctor_profile = itemView.imageView_doctor_profile

        init {
            imgDoctorProfile =
                itemView.findViewById(R.id.imageView_doctor_profile)
            textView_doctor_name = itemView.findViewById(R.id.textView_doctor_name)
            textView_addresss = itemView.findViewById(R.id.textView_address)
            appointment_type = itemView.findViewById(R.id.appointment_type)
        }
    }


    interface ItemClick {
        fun onItemClick(position: Int)
    }

}