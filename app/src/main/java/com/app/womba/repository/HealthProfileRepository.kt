package com.app.womba.repository

import com.app.womba.AppApplication
import com.app.womba.base.BaseModel
import com.app.womba.model.LifeStylePost
import com.app.womba.model.PatientLifeStyleModel
import com.app.womba.model.healthProfile.HealthProfileModel
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
class HealthProfileRepository {

    fun getallMedicalData(returnValue: (HealthProfileModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.getallMedicalData(PatientId(getUserData().data!!.get_id()!!))!!
                .enqueue(object :
                    Callback<HealthProfileModel> {
                    override fun onFailure(call: Call<HealthProfileModel>?, t: Throwable?) {
                        returnValue(HealthProfileModel(t!!.message!!))
                    }

                    override fun onResponse(
                        call: Call<HealthProfileModel>?,
                        response: Response<HealthProfileModel>?
                    ) {
                        when {
                            response!!.body() != null -> returnValue(response.body()!!)
                            response.errorBody() != null -> {
                                val (statusCode, message) = handleJson(
                                    response.errorBody()!!.string()
                                )
                                returnValue(HealthProfileModel(statusCode.toInt(), message))
                            }
                            else -> returnValue(
                                HealthProfileModel(
                                    response.code(),
                                    response.message().toString()
                                )
                            )
                        }
                    }
                })
        } else {
            returnValue(HealthProfileModel(NO_INTERNET_MESSAGE))
        }
    }

    fun getPatientLifeStyle(returnValue: (PatientLifeStyleModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.getPatientLifeStyle(PatientId(getUserData().data!!.get_id()!!))!!
                .enqueue(object :
                    Callback<PatientLifeStyleModel> {
                    override fun onFailure(call: Call<PatientLifeStyleModel>?, t: Throwable?) {
                        returnValue(PatientLifeStyleModel(t!!.message!!))
                    }

                    override fun onResponse(
                        call: Call<PatientLifeStyleModel>?,
                        response: Response<PatientLifeStyleModel>?
                    ) {
                        when {
                            response!!.body() != null -> returnValue(response.body()!!)
                            response.errorBody() != null -> {
                                val (statusCode, message) = handleJson(
                                    response.errorBody()!!.string()
                                )
                                returnValue(PatientLifeStyleModel(statusCode.toInt(), message))
                            }
                            else -> returnValue(
                                PatientLifeStyleModel(
                                    response.code(),
                                    response.message().toString()
                                )
                            )
                        }
                    }
                })
        } else {
            returnValue(PatientLifeStyleModel(NO_INTERNET_MESSAGE))
        }
    }

    fun addMedicalConditions(model: ConditionPost, returnValue: (BaseModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.addMedicalConditions(model)!!.enqueue(object :
                Callback<BaseModel> {
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

    fun addMedicalMedications(model: MedicationsPost, returnValue: (BaseModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.addMedicalMedications(model)!!.enqueue(object :
                Callback<BaseModel> {
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

    fun addMedicalAlergies(model: AlergiesPost, returnValue: (BaseModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.addMedicalAlergies(model)!!.enqueue(object :
                Callback<BaseModel> {
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

    fun addMedicalTreatments(model: TreatmentPost, returnValue: (BaseModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.addMedicalTreatments(model)!!.enqueue(object :
                Callback<BaseModel> {
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

    fun addMedicalImmunization(model: ImmunizationPost, returnValue: (BaseModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.addMedicalImmunization(model)!!.enqueue(object :
                Callback<BaseModel> {
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

    fun addMedicalCovid(model: MedicalCovidPost, returnValue: (BaseModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.addMedicalCovid(model)!!.enqueue(object :
                Callback<BaseModel> {
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



    fun deleteMedicalRecord(model: DeleteMedical, returnValue: (BaseModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.deleteMedicalRecord(model)!!.enqueue(object :
                Callback<BaseModel> {
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

    fun savePatientLifeStyle(model: LifeStylePost, returnValue: (BaseModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.savePatientLifeStyle(model)!!.enqueue(object :
                Callback<BaseModel> {
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