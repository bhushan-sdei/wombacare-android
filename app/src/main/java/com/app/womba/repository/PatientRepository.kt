package com.app.womba.repository

import com.app.womba.AppApplication
import com.app.womba.base.BaseModel
import com.app.womba.model.*
import com.app.womba.model.postResponseModel.*
import com.app.womba.network.RetrofitClient
import com.app.womba.utils.NO_INTERNET_MESSAGE
import com.app.womba.utils.getUserData
import com.app.womba.utils.handleJson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Singleton

@Singleton
class PatientRepository {
    fun getUpcomingAppointments(offset:String,returnValue: (AppointmentModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.getUpcomingAppointments(PatientBookedAppointmentsListPost(
                getUserData().data!!.get_id(),offset,"10"))!!.enqueue(object :
                Callback<AppointmentModel> {
                override fun onFailure(call: Call<AppointmentModel>?, t: Throwable?) {
                    returnValue(AppointmentModel(t!!.message!!))
                }

                override fun onResponse(call: Call<AppointmentModel>?, response: Response<AppointmentModel>?) {
                    when {
                        response!!.body() != null -> returnValue(response.body()!!)
                        response.errorBody() != null -> {
                            val (statusCode, message) = handleJson(response.errorBody()!!.string())
                            returnValue(AppointmentModel(statusCode.toInt(), message))
                        }
                        else -> returnValue(
                            AppointmentModel(
                                response.code(),
                                response.message().toString()
                            )
                        )
                    }
                }
            })
        } else {
            returnValue(AppointmentModel(NO_INTERNET_MESSAGE))
        }
    }

    fun getPastAppointments(returnValue: (AppointmentModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.getPastAppointments(PatientBookedAppointmentsListPost(
                getUserData().data!!.get_id(),"1","10"))!!.enqueue(object :
                Callback<AppointmentModel> {
                override fun onFailure(call: Call<AppointmentModel>?, t: Throwable?) {
                    returnValue(AppointmentModel(t!!.message!!))
                }

                override fun onResponse(call: Call<AppointmentModel>?, response: Response<AppointmentModel>?) {
                    when {
                        response!!.body() != null -> returnValue(response.body()!!)
                        response.errorBody() != null -> {
                            val (statusCode, message) = handleJson(response.errorBody()!!.string())
                            returnValue(AppointmentModel(statusCode.toInt(), message))
                        }
                        else -> returnValue(
                            AppointmentModel(
                                response.code(),
                                response.message().toString()
                            )
                        )
                    }
                }
            })
        } else {
            returnValue(AppointmentModel(NO_INTERNET_MESSAGE))
        }
    }


    fun getPaymentsList(returnValue: (PaymentListModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.getPaymentList(PatientId(getUserData().data!!.get_id()!!))!!.enqueue(object :
                Callback<PaymentListModel> {
                override fun onFailure(call: Call<PaymentListModel>?, t: Throwable?) {
                    returnValue(PaymentListModel(t!!.message!!))
                }

                override fun onResponse(call: Call<PaymentListModel>?, response: Response<PaymentListModel>?) {
                    when {
                        response!!.body() != null -> returnValue(response.body()!!)
                        response.errorBody() != null -> {
                            val (statusCode, message) = handleJson(response.errorBody()!!.string())
                            returnValue(PaymentListModel(statusCode.toInt(), message))
                        }
                        else -> returnValue(
                            PaymentListModel(
                                response.code(),
                                response.message().toString()
                            )
                        )
                    }
                }
            })
        } else {
            returnValue(PaymentListModel(NO_INTERNET_MESSAGE))
        }
    }

    fun generateToken(model:GenerateToken,returnValue: (VideoTokenModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.generateToken(model)!!.enqueue(object :
                Callback<VideoTokenModel> {
                override fun onFailure(call: Call<VideoTokenModel>?, t: Throwable?) {
                    returnValue(VideoTokenModel(t!!.message!!))
                }

                override fun onResponse(call: Call<VideoTokenModel>?, response: Response<VideoTokenModel>?) {
                    when {
                        response!!.body() != null -> returnValue(response.body()!!)
                        response.errorBody() != null -> {
                            val (statusCode, message) = handleJson(response.errorBody()!!.string())
                            returnValue(VideoTokenModel(statusCode.toInt(), message))
                        }
                        else -> returnValue(
                            VideoTokenModel(
                                response.code(),
                                response.message().toString()
                            )
                        )
                    }
                }
            })
        } else {
            returnValue(VideoTokenModel(NO_INTERNET_MESSAGE))
        }
    }

    fun getNotificationList(returnValue: (NotificationListModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.getNotificationList(Notification(getUserData().data!!.get_id()!!))!!.enqueue(object :
                Callback<NotificationListModel> {
                override fun onFailure(call: Call<NotificationListModel>?, t: Throwable?) {
                    returnValue(NotificationListModel(t!!.message!!))
                }

                override fun onResponse(call: Call<NotificationListModel>?, response: Response<NotificationListModel>?) {
                    when {
                        response!!.body() != null -> returnValue(response.body()!!)
                        response.errorBody() != null -> {
                            val (statusCode, message) = handleJson(response.errorBody()!!.string())
                            returnValue(NotificationListModel(statusCode.toInt(), message))
                        }
                        else -> returnValue(
                            NotificationListModel(
                                response.code(),
                                response.message().toString()
                            )
                        )
                    }
                }
            })
        } else {
            returnValue(NotificationListModel(NO_INTERNET_MESSAGE))
        }
    }

}