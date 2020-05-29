package com.app.womba.repository

import android.text.TextUtils
import com.app.womba.AppApplication
import com.app.womba.base.BaseModel
import com.app.womba.model.LanguageModel
import com.app.womba.model.ProfileImageModel
import com.app.womba.model.UserModel
import com.app.womba.model.postResponseModel.*
import com.app.womba.network.RetrofitClient
import com.app.womba.utils.NO_INTERNET_MESSAGE
import com.app.womba.utils.getUserData
import com.app.womba.utils.handleJson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import javax.inject.Singleton

@Singleton
class AuthRepository {

    fun login(model: LoginPost, returnValue: (UserModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.login(
                model

            )!!.enqueue(object : Callback<UserModel> {
                override fun onFailure(call: Call<UserModel>?, t: Throwable?) {
                    returnValue(UserModel(t!!.message!!))
                }

                override fun onResponse(call: Call<UserModel>?, response: Response<UserModel>?) {
                    when {
                        response!!.body() != null -> returnValue(response.body()!!)
                        response.errorBody() != null -> {
                            val (statusCode, message) = handleJson(response.errorBody()!!.string())
                            returnValue(UserModel(statusCode.toInt(), message))
                        }
                        else -> returnValue(
                            UserModel(
                                response.code(),
                                response.message().toString()
                            )
                        )
                    }
                }
            })
        } else {
            returnValue(UserModel(NO_INTERNET_MESSAGE))
        }
    }


    fun forgetPassword(model: ForgetPost, returnValue: (UserModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.forgetPassword(
                model

            )!!.enqueue(object : Callback<UserModel> {
                override fun onFailure(call: Call<UserModel>?, t: Throwable?) {
                    returnValue(UserModel(t!!.message!!))
                }

                override fun onResponse(call: Call<UserModel>?, response: Response<UserModel>?) {
                    when {
                        response!!.body() != null -> returnValue(response.body()!!)
                        response.errorBody() != null -> {
                            val (statusCode, message) = handleJson(response.errorBody()!!.string())
                            returnValue(UserModel(statusCode.toInt(), message))
                        }
                        else -> returnValue(
                            UserModel(
                                response.code(),
                                response.message().toString()
                            )
                        )
                    }
                }
            })
        } else {
            returnValue(UserModel(NO_INTERNET_MESSAGE))
        }
    }

    fun userChangePassword(model: ChangePassword, returnValue: (UserModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.userChangePassword(
                model

            )!!.enqueue(object : Callback<UserModel> {
                override fun onFailure(call: Call<UserModel>?, t: Throwable?) {
                    returnValue(UserModel(t!!.message!!))
                }

                override fun onResponse(call: Call<UserModel>?, response: Response<UserModel>?) {
                    when {
                        response!!.body() != null -> returnValue(response.body()!!)
                        response.errorBody() != null -> {
                            val (statusCode, message) = handleJson(response.errorBody()!!.string())
                            returnValue(UserModel(statusCode.toInt(), message))
                        }
                        else -> returnValue(
                            UserModel(
                                response.code(),
                                response.message().toString()
                            )
                        )
                    }
                }
            })
        } else {
            returnValue(UserModel(NO_INTERNET_MESSAGE))
        }
    }

    fun updateProfile(model: UpdateProfilePost, returnValue: (UserModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.updateProfile(model)!!.enqueue(object : Callback<UserModel> {
                override fun onFailure(call: Call<UserModel>?, t: Throwable?) {
                    returnValue(UserModel(t!!.message!!))
                }

                override fun onResponse(call: Call<UserModel>?, response: Response<UserModel>?) {
                    when {
                        response!!.body() != null -> returnValue(response.body()!!)
                        response.errorBody() != null -> {
                            val (statusCode, message) = handleJson(response.errorBody()!!.string())
                            returnValue(UserModel(statusCode.toInt(), message))
                        }
                        else -> returnValue(
                            UserModel(
                                response.code(),
                                response.message().toString()
                            )
                        )
                    }
                }
            })
        } else {
            returnValue(UserModel(NO_INTERNET_MESSAGE))
        }
    }

    fun getProfile(model: PatientId, returnValue: (UserModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.getProfile(model)!!.enqueue(object : Callback<UserModel> {
                override fun onFailure(call: Call<UserModel>?, t: Throwable?) {
                    returnValue(UserModel(t!!.message!!))
                }

                override fun onResponse(call: Call<UserModel>?, response: Response<UserModel>?) {
                    when {
                        response!!.body() != null -> returnValue(response.body()!!)
                        response.errorBody() != null -> {
                            val (statusCode, message) = handleJson(response.errorBody()!!.string())
                            returnValue(UserModel(statusCode.toInt(), message))
                        }
                        else -> returnValue(
                            UserModel(
                                response.code(),
                                response.message().toString()
                            )
                        )
                    }
                }
            })
        } else {
            returnValue(UserModel(NO_INTERNET_MESSAGE))
        }
    }


    fun register(model: RegistrationPost, returnValue: (BaseModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.register(
                model
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


    fun country(returnValue: (BaseModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.getCountry()!!.enqueue(object : Callback<BaseModel> {
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

    fun getLanguage(returnValue: (LanguageModel) -> Unit) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance?.getLanguage(LanguagePostModel("", ""))!!
                .enqueue(object : Callback<LanguageModel> {
                    override fun onFailure(call: Call<LanguageModel>?, t: Throwable?) {
                        returnValue(LanguageModel(t!!.message!!))
                    }

                    override fun onResponse(
                        call: Call<LanguageModel>?,
                        response: Response<LanguageModel>?
                    ) {
                        when {
                            response!!.body() != null -> returnValue(response.body()!!)
                            response.errorBody() != null -> {
                                val (statusCode, message) = handleJson(
                                    response.errorBody()!!.string()
                                )
                                returnValue(LanguageModel(statusCode.toInt(), message))
                            }
                            else -> returnValue(
                                LanguageModel(
                                    response.code(),
                                    response.message().toString()
                                )
                            )
                        }
                    }
                })
        } else {
            returnValue(LanguageModel(NO_INTERNET_MESSAGE))
        }
    }

    fun updateImage(
        filepath: String, returnValue: (ProfileImageModel) -> Unit
    ) {
        if (AppApplication.instance!!.hasNetwork()) {
            RetrofitClient.instance!!.updateImage(
                createBuilder(
                    filepath
                )
            ).enqueue(object : Callback<ProfileImageModel> {
                override fun onFailure(call: Call<ProfileImageModel>?, t: Throwable?) {
                    returnValue(ProfileImageModel(t!!.message!!))
                }

                override fun onResponse(
                    call: Call<ProfileImageModel>?,
                    response: Response<ProfileImageModel>?
                ) {
                    when {
                        response!!.body() != null -> returnValue(response.body()!!)
                        response.errorBody() != null -> {
                            val (statusCode, message) = handleJson(response.errorBody()!!.string())
                            returnValue(ProfileImageModel(statusCode.toInt(), message))
                        }
                        else -> returnValue(
                            ProfileImageModel(
                                response.code(),
                                response.message().toString()
                            )
                        )
                    }
                }
            })
        } else {
            returnValue(ProfileImageModel(NO_INTERNET_MESSAGE))
        }

    }


    private fun createBuilder(
        filepath: String
    ): RequestBody {

        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)

        builder.addFormDataPart("id", getUserData().data!!.get_id()!!)
            .addFormDataPart("userType", "Patient")

        if (!TextUtils.isEmpty(filepath)) {
            val file = File(filepath)
            if (file.exists()) {
                builder.addFormDataPart(
                    "file",
                    file.name,
                    RequestBody.create("image/jpg".toMediaTypeOrNull(), file)
                )
            }
        }
        return builder.build()
    }


}