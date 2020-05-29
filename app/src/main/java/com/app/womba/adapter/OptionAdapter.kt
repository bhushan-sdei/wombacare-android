package com.app.womba.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.womba.R
import com.app.womba.interfaces.InterfacesCall
import com.app.womba.utils.OptionsModel
import kotlinx.android.synthetic.main.item_options.view.*
import java.util.*

class OptionAdapter(var con: Context,
                    var mData: ArrayList<OptionsModel>,
                    var mClick: InterfacesCall.IndexClick) : RecyclerView.Adapter<OptionAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_options, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtOption.text = mData[position].name
        holder.llOption.setOnClickListener {
            mClick.clickIndex(position)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val llOption = itemView.llOption!!
        val txtOption = itemView.txtOption!!
    }

}