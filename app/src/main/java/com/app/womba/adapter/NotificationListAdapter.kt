package com.app.womba.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.womba.R
import com.app.womba.model.NotificationListModel
import kotlinx.android.synthetic.main.item_notification.view.*

class NotificationListAdapter(var context: Context,
                              var mData: ArrayList<NotificationListModel.DataBean>) : RecyclerView.Adapter<NotificationListAdapter.BindingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false)
        return BindingHolder(view)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.txtName.text = mData[position].notification
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class BindingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtName = itemView.txtName
    }

}