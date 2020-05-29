package com.app.womba.repository

import com.app.womba.AppApplication
import com.app.womba.base.BaseModel
import com.app.womba.network.RetrofitClient
import com.app.womba.utils.NO_INTERNET_MESSAGE
import com.app.womba.utils.handleJson
import com.app.womba.model.fullreport.HealthModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Singleton

@Singleton
class BinahRepository {

    fun saveEvitalsData(model: HealthModel, returnValue: (BaseModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.saveEvitalsData(model

            )!!.enqueue(object : Callback<BaseModel> {
                override fun onFailure(call: Call<BaseModel>?, t: Throwable?) {
                    returnValue(BaseModel(t!!.message!!))
                }

                override fun onResponse(call: Call<BaseModel>?, response: Response<BaseModel>?) {
                    when {
                        response!!.body() != null -> returnValue(response.body()!!)
                        response.errorBody() != null -> {
                            val (statusCode, message) = handleJson(response.errorBody()!!.string())
                            returnValue(BaseModel(statusCode.toInt(), message))
                        }
                        else -> returnValue(
                            BaseModel(
                                response.code(),
                                response.message().toString()
                            )
                        )
                    }
                }
            })
        } else {
            returnValue(BaseModel(NO_INTERNET_MESSAGE))
        }
    }


}