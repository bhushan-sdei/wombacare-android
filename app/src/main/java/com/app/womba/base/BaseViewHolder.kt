package com.app.womba.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by shubham on 28/11/19.
 */

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(position: Int)
}
