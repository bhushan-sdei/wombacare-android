package com.app.womba.network

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.app.womba.AppApplication
import com.app.womba.utils.InterConsts
import com.app.womba.utils.getAppPref
import com.squareup.okhttp.Request
import kotlinx.io.IOException
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


object RetrofitClient {

    private var retrofit: Retrofit? = null
    private var apiInterface: ApiInterface? = null

    val instance: ApiInterface?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(WS_BASE_URL)
                    .client(provideOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            if (apiInterface == null) {
                apiInterface = retrofit!!.create(ApiInterface::class.java)
            }
            return apiInterface
        }


    //Creating OKHttpClient
    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .cache(provideCache())
            .addInterceptor(provideOfflineCacheInterceptor())
            .addNetworkInterceptor(provideCacheInterceptor())
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    //Creating Cache
    private fun provideCache(): Cache? {
        var cache: Cache? = null
        try {
            cache = Cache(
                File(AppApplication.instance!!.cacheDir, "http-cache"),
                (10 * 1024 * 1024).toLong()
            ) // 10 MB
        } catch (ignored: Exception) {

        }
        return cache
    }
//

    private fun provideCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())

            response.newBuilder()
                .addHeader("authorization", getAppPref().getString(InterConsts.AUTH_TOKEN)!!)
                .build()
        }
    }

//
    //Provides offline cache
    private fun provideOfflineCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()

            if (AppApplication.instance!!.hasNetwork()) {

                request = request.newBuilder()
                    .addHeader("authorization", getAppPref().getString(InterConsts.AUTH_TOKEN)!!)
                    .build()
            }
            chain.proceed(request)
        }
    }

}
