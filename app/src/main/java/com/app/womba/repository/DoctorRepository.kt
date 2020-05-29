package com.app.womba.repository

import com.app.womba.AppApplication
import com.app.womba.base.BaseModel
import com.app.womba.model.*
import com.app.womba.model.postResponseModel.*
import com.app.womba.network.RetrofitClient
import com.app.womba.utils.NO_INTERNET_MESSAGE
import com.app.womba.utils.handleJson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Singleton


@Singleton
class DoctorRepository {

    fun getAllDoctorsList(model:ListPostResponse,returnValue: (DoctorListResponse) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.getAllDoctorsList(model)!!.enqueue(object : Callback<DoctorListResponse> {
                override fun onFailure(call: Call<DoctorListResponse>?, t: Throwable?) {
                    returnValue(DoctorListResponse(t!!.message!!))
                }

                override fun onResponse(call: Call<DoctorListResponse>?, response: Response<DoctorListResponse>?) {
                    when {
                        response!!.body() != null -> returnValue(response.body()!!)
                        response.errorBody() != null -> {
                            val (statusCode, message) = handleJson(response.errorBody()!!.string())
                            returnValue(DoctorListResponse(statusCode.toInt(), message))
                        }
                        else -> returnValue(
                            DoctorListResponse(
                                response.code(),
                                response.message().toString()
                            )
                        )
                    }
                }
            })
        } else {
            returnValue(DoctorListResponse(NO_INTERNET_MESSAGE))
        }
    }

    fun educationList(model: DoctorEducationPost,returnValue: (DoctorEducationResponse) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.educationList(model)!!.enqueue(object : Callback<DoctorEducationResponse> {
                override fun onFailure(call: Call<DoctorEducationResponse>?, t: Throwable?) {
                    returnValue(DoctorEducationResponse(t!!.message!!))
                }

                override fun onResponse(call: Call<DoctorEducationResponse>?, response: Response<DoctorEducationResponse>?) {
                    when {
                        response!!.body() != null -> returnValue(response.body()!!)
                        response.errorBody() != null -> {
                            val (statusCode, message) = handleJson(response.errorBody()!!.string())
                            returnValue(DoctorEducationResponse(statusCode.toInt(), message))
                        }
                        else -> returnValue(
                            DoctorEducationResponse(
                                response.code(),
                                response.message().toString()
                            )
                        )
                    }
                }
            })
        } else {
            returnValue(DoctorEducationResponse(NO_INTERNET_MESSAGE))
        }
    }

    fun getDoctorSlotByDate(model: DoctorSlotTimePost, returnValue: (DoctorSlotModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.getDoctorSlotByDate(model)!!.enqueue(object : Callback<DoctorSlotModel> {
                override fun onFailure(call: Call<DoctorSlotModel>?, t: Throwable?) {
                    returnValue(DoctorSlotModel(t!!.message!!))
                }

                override fun onResponse(call: Call<DoctorSlotModel>?, response: Response<DoctorSlotModel>?) {
                    when {
                        response!!.body() != null -> returnValue(response.body()!!)
                        response.errorBody() != null -> {
                            val (statusCode, message) = handleJson(response.errorBody()!!.string())
                            returnValue(DoctorSlotModel(statusCode.toInt(), message))
                        }
                        else -> returnValue(
                            DoctorSlotModel(
                                response.code(),
                                response.message().toString()
                            )
                        )
                    }
                }
            })
        } else {
            returnValue(DoctorSlotModel(NO_INTERNET_MESSAGE))
        }
    }

    fun bookAppointment(model: PaymentModel, returnValue: (BookingResponse) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.bookAppointment(model)!!.enqueue(object : Callback<BookingResponse> {
                override fun onFailure(call: Call<BookingResponse>?, t: Throwable?) {
                    returnValue(BookingResponse(t!!.message!!))
                }

                override fun onResponse(call: Call<BookingResponse>?, response: Response<BookingResponse>?) {
                    when {
                        response!!.body() != null -> returnValue(response.body()!!)
                        response.errorBody() != null -> {
                            val (statusCode, message) = handleJson(response.errorBody()!!.string())
                            returnValue(BookingResponse(statusCode.toInt(), message))
                        }
                        else -> returnValue(
                            BookingResponse(
                                response.code(),
                                response.message().toString()
                            )
                        )
                    }
                }
            })
        } else {
            returnValue(BookingResponse(NO_INTERNET_MESSAGE))
        }
    }

}