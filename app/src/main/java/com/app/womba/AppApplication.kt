package com.app.womba

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.multidex.MultiDexApplication
import com.facebook.FacebookSdk

class AppApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

   fun hasNetwork(): Boolean {
        return instance!!.checkIfHasNetwork()
    }

    fun checkIfHasNetwork(): Boolean {
        var result = false
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    result = true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    result = true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                    return true
                }
            }
        } else {
            val activeNetwork = cm.activeNetworkInfo
            if (activeNetwork != null) {
                // connected to the internet
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                    result = true
                } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                    result = true
                } else if (activeNetwork.type == ConnectivityManager.TYPE_VPN) {
                    return true
                }
            }
        }
        return result
    }

    companion object {
        val TAG = AppApplication::class.java.simpleName
        var instance: AppApplication? = null
            private set
    }

}
