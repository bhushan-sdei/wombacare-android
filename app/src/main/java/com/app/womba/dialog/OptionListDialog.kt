package com.app.womba.dialog

import android.content.Context
import android.os.Build
import android.view.Gravity
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.womba.adapter.OptionAdapter
import com.app.womba.base.BaseListDialog
import com.app.womba.interfaces.InterfacesCall
import com.app.womba.utils.OptionsModel
import kotlinx.android.synthetic.main.dialog_options.*


class OptionListDialog(
    context: Context,
    themeResId: Int,
    private val LayoutId: Int,
    var list: ArrayList<OptionsModel>,
    title: String,
    private val callback: InterfacesCall.Callback
) : BaseListDialog(context, themeResId) {

    lateinit var mAdapter: OptionAdapter
    var title: String
    init {
        val wmlp = this.window!!.attributes
        wmlp.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        window!!.attributes = wmlp
        this.title = title
    }

    override fun getInterfaceInstance(): InterfacesCall.IndexClick {
        return this
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateStuff() {
        if (list.isNotEmpty()) {
            recyclerView!!.layoutManager = LinearLayoutManager(context)
            mAdapter = OptionAdapter(context, list, indexClick)
            recyclerView!!.adapter = mAdapter
            txtTitle.text = title
        }
        imgClose.setOnClickListener {
            dismiss()
        }
    }

    override fun getContentView(): Int {
        return LayoutId
    }

    override fun clickIndex(pos: Int) {
        dismiss()
        callback.selected(pos)
        mAdapter.notifyDataSetChanged()
    }
}
