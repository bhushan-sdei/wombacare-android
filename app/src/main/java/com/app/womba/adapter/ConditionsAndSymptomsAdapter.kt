package com.app.womba.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.womba.R
import com.app.womba.interfaces.HealthInterface
import com.app.womba.model.postResponseModel.Condition
import com.app.womba.model.postResponseModel.ConditionPost
import kotlinx.android.synthetic.main.item_conditons.view.*

class ConditionsAndSymptomsAdapter(
    var context: Context,
    var mData: ArrayList<Condition>,
    var mClick: HealthInterface
) : RecyclerView.Adapter<ConditionsAndSymptomsAdapter.BindingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_conditons, parent, false)
        return BindingHolder(view)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.lblSpeciality.text=mData[position].conditionName

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