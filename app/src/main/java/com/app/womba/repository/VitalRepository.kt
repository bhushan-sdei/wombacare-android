package com.app.womba.repository

import com.app.womba.AppApplication
import com.app.womba.base.BaseModel
import com.app.womba.model.AppointmentModel
import com.app.womba.model.TrendSubModel
import com.app.womba.model.VitalHistoryData
import com.app.womba.model.VitalsTrendResponse
import com.app.womba.model.postResponseModel.PatientBookedAppointmentsListPost
import com.app.womba.model.postResponseModel.ScanHistoryPostModel
import com.app.womba.model.postResponseModel.VitalPost
import com.app.womba.network.RetrofitClient
import com.app.womba.utils.NO_INTERNET_MESSAGE
import com.app.womba.utils.getUserData
import com.app.womba.utils.handleJson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Singleton


@Singleton
class VitalRepository {

    fun getSaveEVitalsDataGraph(model:VitalPost,returnValue: (VitalsTrendResponse) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.getSaveEVitalsDataGraph(
                model
            )!!.enqueue(object :
                Callback<VitalsTrendResponse> {
                override fun onFailure(call: Call<VitalsTrendResponse>?, t: Throwable?) {
                    returnValue(VitalsTrendResponse(t!!.message!!))
                }

                override fun onResponse(call: Call<VitalsTrendResponse>?, response: Response<VitalsTrendResponse>?) {
                    when {
                        response!!.body() != null -> returnValue(response.body()!!)
                        response.errorBody() != null -> {
                            val (statusCode, message) = handleJson(response.errorBody()!!.string())
                            returnValue(VitalsTrendResponse(statusCode.toInt(), message))
                        }
                        else -> returnValue(
                            VitalsTrendResponse(
                                response.code(),
                                response.message().toString()
                            )
                        )
                    }
                }
            })
        } else {
            returnValue(VitalsTrendResponse(NO_INTERNET_MESSAGE))
        }
    }


    fun getEvitalsHistorydata(model: ScanHistoryPostModel, returnValue: (VitalHistoryData) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.getEvitalsHistorydata(
                model
            )!!.enqueue(object :
                Callback<VitalHistoryData> {
                override fun onFailure(call: Call<VitalHistoryData>?, t: Throwable?) {
                    returnValue(VitalHistoryData(t!!.message!!))
                }

                override fun onResponse(call: Call<VitalHistoryData>?, response: Response<VitalHistoryData>?) {
                    when {
                        response!!.body() != null -> returnValue(response.body()!!)
                        response.errorBody() != null -> {
                            val (statusCode, message) = handleJson(response.errorBody()!!.string())
                            returnValue(VitalHistoryData(statusCode.toInt(), message))
                        }
                        else -> returnValue(
                            VitalHistoryData(
                                response.code(),
                                response.message().toString()
                            )
                        )
                    }
                }
            })
        } else {
            returnValue(VitalHistoryData(NO_INTERNET_MESSAGE))
        }
    }

}