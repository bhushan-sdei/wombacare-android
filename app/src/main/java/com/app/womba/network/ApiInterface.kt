package com.app.womba.network

import com.app.womba.base.BaseModel
import com.app.womba.model.*
import com.app.womba.model.fullreport.HealthModel
import com.app.womba.model.healthProfile.HealthProfileModel
import com.app.womba.model.postResponseModel.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiInterface {

    @POST(WS_USER_AUTHENTICATION)
    fun login(
        @Body model: LoginPost
    ): Call<UserModel>

    @POST(WS_USER_FORGOT_PASSWORD)
    fun forgetPassword(
        @Body model: ForgetPost
    ): Call<UserModel>

    @POST(WS_PATIENT_REGISTERATION)
    fun register(
        @Body model: RegistrationPost
    ): Call<BaseModel>

    @POST(WS_UPDATE_BASIC_INFORMATION)
    fun updateProfile(
        @Body model: UpdateProfilePost
    ): Call<UserModel>

    @POST(WS_GET_PATIENT_BASIC_INFO)
    fun getProfile(
        @Body model: PatientId
    ): Call<UserModel>

    @GET(WS_GET_COUNTRY)
    fun getCountry(): Call<BaseModel>

    @POST(WS_GET_Language)
    fun getLanguage(@Body model: LanguagePostModel): Call<LanguageModel>

    @POST(WS_GET_CITY)
    fun getCity(@Body model: RegistrationPost): Call<BaseModel>

    @POST(WS_GET_STATE)
    fun getState(@Body model: RegistrationPost): Call<BaseModel>

    @POST(WS_GET_ALL_DOCTOR_LIST)
    fun getAllDoctorsList(@Body model: ListPostResponse): Call<DoctorListResponse>

    @POST(WS_GET_DOCTOR_EDUCATION_LIST)
    fun educationList(@Body model: DoctorEducationPost): Call<DoctorEducationResponse>

    @POST(WS_GET_DOCTOR_SPECAILITY_LIST)
    fun specialityList(@Body model: DoctorSpecialityPost): Call<DoctorEducationResponse>

    @POST(WS_PATIENT_BOOKED_APPOINTMENT)
    fun getUpcomingAppointments(@Body model: PatientBookedAppointmentsListPost): Call<AppointmentModel>

    @POST(WS_PATIENT_PAST_APPOINTMENT)
    fun getPastAppointments(@Body model: PatientBookedAppointmentsListPost): Call<AppointmentModel>

    @POST(WS_PATIENT_PAYMENT_LIST)
    fun getPaymentList(@Body model: PatientId): Call<PaymentListModel>

    @POST(WS_DOCTOR_SCHDULE_BY_DATE)
    fun getDoctorSlotByDate(@Body model: DoctorSlotTimePost): Call<DoctorSlotModel>

    @POST(WS_BOOK_APPOINTMENT)
    fun bookAppointment(@Body model: PaymentModel): Call<BookingResponse>

    @POST(WS_GENERATE_TOKEN)
    fun generateToken(@Body model: GenerateToken): Call<VideoTokenModel>

    @POST(WS_SAVE_EVITALS_DATA)
    fun saveEvitalsData(@Body model: HealthModel): Call<BaseModel>

    @POST(WS_NOTIFICATION_LIST)
    fun getNotificationList(@Body model: Notification): Call<NotificationListModel>

    @POST("/api/getallMedicalData")
    fun getallMedicalData(@Body model: PatientId): Call<HealthProfileModel>

   @POST("/api/getPatientLifeStyle")
    fun getPatientLifeStyle(@Body model: PatientId): Call<PatientLifeStyleModel>

    @POST("api/addMedicalConditions")
    fun addMedicalConditions(@Body model: ConditionPost): Call<BaseModel>

    @POST("/api/addMedicalMedications")
    fun addMedicalMedications(@Body model: MedicationsPost): Call<BaseModel>

    @POST("/api/addMedicalAlergies")
    fun addMedicalAlergies(@Body model: AlergiesPost): Call<BaseModel>

    @POST("/api/addMedicalTreatments")
    fun addMedicalTreatments(@Body model: TreatmentPost): Call<BaseModel>

    @POST("/api/addMedicalImmunization")
    fun addMedicalImmunization(@Body model: ImmunizationPost): Call<BaseModel>

    @POST("/api/addMedicalCovid")
    fun addMedicalCovid(@Body model: MedicalCovidPost): Call<BaseModel>

    @POST("/api/savePatientLifeStyle")
    fun savePatientLifeStyle(@Body model: LifeStylePost): Call<BaseModel>

    @POST("/api/deleteMedicalRecord")
    fun deleteMedicalRecord(@Body model: DeleteMedical): Call<BaseModel>

    @POST("/api/getSaveEVitalsDataGraph")
    fun getSaveEVitalsDataGraph(@Body model: VitalPost): Call<VitalsTrendResponse>
    @POST("/api/getEvitalsHistorydata")
    fun getEvitalsHistorydata(@Body model: ScanHistoryPostModel): Call<VitalHistoryData>

    @POST("/api/updateImage")
    fun updateImage(@Body file: RequestBody):
            Call<ProfileImageModel>

    @POST("/api/userChangePassword")
    fun userChangePassword(@Body file: ChangePassword):Call<UserModel>


//
//  @FormUrlEncoded
//    @POST("/resetPassword")
//    fun resetPassword(
//        @Field("currentPassword") email: String,
//        @Field("newPassword") password: String
//    ): Call<LoginModel>
//
//    @FormUrlEncoded
//    @POST("/forgotPassword")
//    fun forgotPassword(@Field("email") email: String): Call<BaseModel>
//
//    @FormUrlEncoded
//    @POST("/updateSignupProfile")
//    fun updateSignupProfile(
//        @Field("name") name: String,
//        @Field("deviceToken") deviceToken: String
//    ): Call<LoginModel>
//
//    @FormUrlEncoded
//    @POST("/updateSignupProfile")
//    fun updateSignupProfile(
//        @Field("lat") lat: String,
//        @Field("lng") lng: String,
//        @Field("city") city: String,
//        @Field("country") country: String,
//        @Field("state") state: String,
//        @Field("deviceToken") deviceToken: String
//    ): Call<LoginModel>
//
//
//    @POST("/updateSignupProfile")
//    fun updateSignupProfile(@Body pets: PetArrayModel): Call<LoginModel>
//
//
//    @POST("/updatePetProfile")
//    fun updatePetProfile(@Body pets: PetArrayModel): Call<LoginModel>
//
//    @FormUrlEncoded
//    @POST("/updateSignupProfile")
//    fun updateSignupProfile(
//        @Field("birthdate") birthdate: String
//    ): Call<LoginModel>
//
//
//    @FormUrlEncoded
//    @POST("/updateSignupProfile")
//    fun updateSignupProfileAboutPet(
//        @Field("aboutPet") aboutPet: String
//    ): Call<LoginModel>
//
//
//    @FormUrlEncoded
//    @POST("/updateSignupProfile")
//    fun updateSignupProfilemyPreferences(
//        @Field("myPreferences") myPreferences: String
//    ): Call<LoginModel>
//
//    @FormUrlEncoded
//    @POST("/updateSignupProfile")
//    fun updateSignupProfileGender(
//        @Field("gender") gender: String,
//        @Field("intrestedIn") intrestedIn: ArrayList<String>
//    ): Call<LoginModel>
//
//    @FormUrlEncoded
//    @POST("/fbSignup")
//    fun fbSignup(
//        @Field("email") email: String,
//        @Field("fbId") fbId: String,
//        @Field("deviceToken") deviceToken: String
//    ): Call<LoginModel>
//
//    @FormUrlEncoded
//    @POST("/confirmPhoneOtp")
//    fun confirmPhoneOtp(
//        @Field("phoneNumber") phoneNumber: String,
//        @Field("otp") otp: String,
//        @Field("deviceToken") deviceToken: String
//    ): Call<OtpLoginModel>
//
//
//    @FormUrlEncoded
//    @POST("/deleteDisplayImage")
//    fun deleteDisplayImage(
//        @Field("imageName") imageName: String
//    ): Call<LoginModel>
//
//    @POST("/updateProfile")
//    fun updateProfile(@Body file: RequestBody): Call<LoginModel>
//
//    @POST("/uploadProfileImage")
//    fun uploadProfileImage(@Body file: RequestBody): Call<LoginModel>
//
//    @FormUrlEncoded
//    @POST("/updateSignupProfile")
//    fun updateProfile(
//        @Field("email") email: String,
//        @Field("name") name: String,
//        @Field("deviceToken") deviceToken: String,
//        @Field("birthdate") birthdate: String,
//        @Field("aboutPet") aboutPet: String,
//        @Field("gender") gender: String,
//        @Field("university") university: String,
//        @Field("occupation") occupation: String,
//        @Field("height") height: String,
//        @Field("intrestedIn") intrestedIn: ArrayList<String>,
//        @Field("myPreferences") myPreferences: String
//    ): Call<LoginModel>
//
//    @FormUrlEncoded
//    @POST("/updateProfile")
//    fun updateProfile(
//        @Field("lat") lat: String,
//        @Field("lng") lng: String
//    ): Call<LoginModel>
//
//
//    @POST("/selectMatchedProfiles")
//    fun selectMatchedProfiles(): Call<CardsModel>
//
//    @FormUrlEncoded
//    @POST("/updateFavourateUsers")
//    fun updateFavourateUsers(
//        @Field("matchedUserId") matchedUserId: String,
//        @Field("action") action: String,
//        @Field("likeDislikeAgain") likeDislikeAgain: String
//    ): Call<MatchedModel>
//
//
//    @FormUrlEncoded
//    @POST("/dislikeUser")
//    fun dislikeUser(
//        @Field("dislikedUserId") dislikedUserId: String
//    ): Call<MatchedModel>
//
//    @FormUrlEncoded
//    @POST("/blockUnblockUser")
//    fun blockUnblockUser(@Field("blockedUserId") blockedUserId: String,
//        @Field("type") type: String): Call<MatchedModel>
//
//    @GET("/blockUserListing")
//    fun blockUserListing(): Call<FriendsModel>
//
//    @GET("/friendList")
//    fun friendList(): Call<FriendsModel>
//
//    @GET("/getFriendMessageList")
//    fun getFriendMessageList(): Call<MessagesModel>
//
//    @FormUrlEncoded
//    @POST("/getChatHistory")
//    fun getChatHistory(@Field("conversationId") conversationId: String): Call<ConversationModel>
//
//    @FormUrlEncoded
//    @POST("/createConversation")
//    fun createConversation(
//        @Field("senderId") senderId: String,
//        @Field("receiverId") receiverId: String
//    ): Call<StartConversationModel>
//
//    @FormUrlEncoded
//    @POST("/unmatchUser")
//    fun unmatchUser(
//        @Field("unmatchedUserId") unmatchedUserId: String,
//        @Field("reason") reason: String
//    ): Call<ConversationModel>
//
//    @FormUrlEncoded
//    @POST("/reportUser")
//    fun reportUser(
//        @Field("reportedUserId") reportedUserId: String,
//        @Field("reason") reason: String
//    ): Call<BaseModel>
//
//    @FormUrlEncoded
//    @POST("/sendInvite")
//    fun sendInvite(@Field("invitedEmail") invitedEmail: String): Call<BaseModel>
//
//    @FormUrlEncoded
//    @POST("/deletePet")
//    fun deletePet(@Field("petId") petId: String): Call<LoginModel>
//
//    @FormUrlEncoded
//    @POST("/deletePetImages")
//    fun deletePetImages(
//        @Field("petId") petId: String,
//        @Field("imageName") imageName: String
//    ): Call<LoginModel>
//
//    @FormUrlEncoded
//    @POST("oauth/access_token")
//    fun access_token(
//        @Field("client_id") client_id: String,
//        @Field("client_secret") client_secret: String,
//        @Field("grant_type") grant_type: String,
//        @Field("redirect_uri") redirect_uri: String,
//        @Field("code") code: String,
//        @Field("scope") scope: String
//    ): Call<InstaAccessModel>
//
//    @GET("/v1.0/{user_id}/")
//    fun graphMediaByID(
//        @Path("user_id") userId: String,
//        @Query("fields") fields: String,
//        @Query("access_token") access_token: String
//    ): Call<InstagramModel>
//
//    @GET("/v1.0/{user_id}/media/")
//    fun graphMedia(
//        @Path("user_id") userId: String,
//        @Query("access_token") access_token: String,
//        @Query("limit") limit: String
//    ): Call<InstaMediaModel>
//
//    @POST("/getDeals")
//    fun getDeals(
//
//    ): Call<DealsModel>
//
//    @FormUrlEncoded
//    @POST("/getDeals")
//    fun getDeals(
//        @Field("maxDistance") maxDistance: String,
//        @Field("lat") lat: String,
//        @Field("lng") lng: String
//    ): Call<DealsModel>
//
//    @POST("/uploadPetImages")
//    fun uploadPetImages(@Body file: RequestBody): Call<LoginModel>
//
//    @FormUrlEncoded
//    @POST("/deleteAccount")
//    fun deleteAccount(
//        @Field("reason") reason: String,
//        @Field("comments") comments: String
//    ): Call<BaseModel>
//
//    @FormUrlEncoded
//    @POST("/giveFeedback")
//    fun giveFeedback(
//        @Field("satisfied") satisfied: String,
//        @Field("comments") comments: String,
//        @Field("improved") improved:  ArrayList<String>
//    ): Call<BaseModel>
//
//    @GET("/deactivateAccount")
//    fun deactivateAccount(): Call<BaseModel>
//
//    @GET("/logoutUser")
//    fun logoutUser(): Call<BaseModel>
//
//    @FormUrlEncoded
//    @POST("/selectMatchedProfiles")
//    fun selectMatchedProfiles(
//        @Field("maxDist") maxDist: String,
//        @Field("maxAge") maxAge: String,
//        @Field("minAge") minAge: String,
//        @Field("interestedIn") interestedIn: ArrayList<String>,
//        @Field("lat") lat: String,
//        @Field("lng") lng: String
//    ): Call<CardsModel>

}
