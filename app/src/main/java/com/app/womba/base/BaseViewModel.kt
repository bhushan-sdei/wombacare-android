package com.app.womba.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.lang.ref.WeakReference


/**
 * Created by shubham on 28/11/19.
 */

abstract class BaseViewModel<N>(application: Application) : AndroidViewModel(application) {

    var isLoading: MutableLiveData<Boolean>? = null

    private var navigator: WeakReference<N>? = null

    var mNavigator: N
        get() = navigator?.get()!!
        set(navigator) {
            this.navigator = WeakReference(navigator)
        }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading?.value=isLoading
    }

    fun getLoader(): MutableLiveData<Boolean> {
        if (isLoading == null) {
            isLoading = MutableLiveData()
        }
        return isLoading as MutableLiveData<Boolean>
    }

}
