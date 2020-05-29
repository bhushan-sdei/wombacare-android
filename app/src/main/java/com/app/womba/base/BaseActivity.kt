package com.app.womba.base

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

/**
 * Created by shubham on 22/05/19.
 */

abstract class BaseActivity<T : ViewDataBinding, V : ViewModel> : AppCompatActivity() {

    // since its going to be common for all the activities
    var mViewModel: V? = null

    var mViewDataBinding: T? = null

    lateinit var mContext: Context

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int

    /**
     * @return toolbar resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V

    /**
     *
     * @return context
     */
    protected abstract val context: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        this.mContext = context
        performDataBinding()
        onCreate()
        initListeners()

    }

    abstract fun onCreate()

    abstract fun initListeners()

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        this.mViewModel = if (mViewModel == null) viewModel else mViewModel
        mViewDataBinding!!.setVariable(bindingVariable, mViewModel)
        mViewDataBinding!!.executePendingBindings()
    }

}

