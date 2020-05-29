package com.app.womba.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.womba.R
import com.app.womba.interfaces.HealthInterface
import com.app.womba.model.postResponseModel.MedicalCovid
import kotlinx.android.synthetic.main.item_conditons.view.*

class CovidAdapter(
    var context: Context,
    var mData: ArrayList<MedicalCovid>,
    var mClick: HealthInterface
) : RecyclerView.Adapter<CovidAdapter.BindingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_conditons, parent, false)
        return BindingHolder(view)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.lblSpeciality.setText(mData[position].AreyouInfected)
        holder.delete_button.setOnClickListener {
            mClick.onDeleteClick(position)
        }
        holder.edit_button.setOnClickListener {
            mClick.onEditClick(position)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class BindingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var edit_button = itemView.edit_button
        var delete_button = itemView.delete_button
        var lblSpeciality = itemView.lblSpeciality

    }

}