package com.app.womba.adapter.vitalhistoryadapter

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.womba.R
import com.app.womba.model.VitalHistoryData
import com.app.womba.ui.vitalHistory.VitalLogFragment
import com.app.womba.utils.changeDateFormat
import com.app.womba.utils.getDateTimeforDefaultTimeZone
import io.github.luizgrp.sectionedrecyclerviewadapter.Section
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters

class ContactsSection internal constructor(
    private val context: Context,
    private val title: String, private val list: List<VitalHistoryData.DataBean.DataX>,
    private val clickListener: VitalLogFragment
) : Section(
    SectionParameters.builder()
        .itemResourceId(R.layout.adapter_vital_history_item_child_layout)
        .headerResourceId(R.layout.adapter_vital_history_header_child_layout)
        .build()
) {
    override fun getContentItemsTotal(): Int {
        return list.size
    }

    override fun getItemViewHolder(view: View): RecyclerView.ViewHolder {
        return ItemViewHolder(view)
    }

    override fun onBindItemViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val itemHolder = holder as ItemViewHolder
        val contact = list[position]
        contact.timeScan?.let {
            itemHolder.timeTxt.text =it
//                changeDateFormat("dd-MM-yyyy HH-mm-ss", "HH:mm", getDateTimeforDefaultTimeZone(it))
        }
        contact.report?.getHr()?.getMeanPulseRate()?.getValue()?.let {
            itemHolder.heart.text = it.toString()
        }

        contact.report?.getRespiration()?.getValue()?.let {
            itemHolder.respiration.text = it.toString()
        }
        contact.report?.getStress()?.getValue()?.let {
            showStressLevel(itemHolder.stress, it)
        }
        itemHolder.parentLayout.setOnClickListener { v: View? ->
            v?.let { clickListener.onItemClick(it, position, contact) }
        }
    }

    private fun showStressLevel(itemHolder: TextView, target: Int) = when (target) {
        in 0..80 -> {
            itemHolder.text = context.getString(R.string.low)
            itemHolder.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.drawable.ic_happy_blue,
                0,
                0,
                0
            )
        }
        in 80..150 -> {
            itemHolder.text = context.getString(R.string.normal)
            itemHolder.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.drawable.ic_smile_blue,
                0,
                0,
                0
            )
        }
        in 150..400 -> {
            itemHolder.text = context.getString(R.string.mild)
            itemHolder.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.drawable.ic_sceptic_blue,
                0,
                0,
                0
            )
        }
        in 400..800 -> {
            itemHolder.text = context.getString(R.string.high)
            itemHolder.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.drawable.ic_sad_blue,
                0,
                0,
                0
            )
        }
        else -> {
            itemHolder.text = context.getString(R.string.extremely_high)
            itemHolder.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.drawable.ic_very_sad_blue,
                0,
                0,
                0
            )
        }

    }

    override fun getHeaderViewHolder(view: View): RecyclerView.ViewHolder {
        return HeaderViewHolder(view)
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder) {
        val headerHolder =
            holder as HeaderViewHolder
        headerHolder.dateTxt.text = changeDateFormat("dd-MM-yyyy", "MMMM dd,yyyy", title)
    }

    internal interface ClickListener {
        fun onItemClick(view: View, position: Int, data:  VitalHistoryData.DataBean.DataX)
    }

    inner class ItemViewHolder(val rootView: View) :
        RecyclerView.ViewHolder(rootView) {
        val parentLayout: ConstraintLayout = rootView.findViewById(R.id.rootView)
        val timeTxt: TextView = rootView.findViewById(R.id.timeTxt)
        val respiration: TextView = rootView.findViewById(R.id.respiration)
        val heart: TextView = rootView.findViewById(R.id.heart)
        val stress: TextView = rootView.findViewById(R.id.stress)

    }

    inner class HeaderViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val dateTxt: TextView = view.findViewById(R.id.dateTxt)

    }

}