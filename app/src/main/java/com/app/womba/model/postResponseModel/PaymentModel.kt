package com.app.womba.model.postResponseModel

data class PaymentModel(
    val appointmentDate: String,
    val doctorId: String,
    val endTime: String,
    val patientId: String,
    val reason: String,
    val startTime: String,
    val stripe_token: StripeToken,
    val userTimezone: String
)

data class StripeToken(
    val id: String,
    val `object`: String
)

/*
{
    "startTime" : "2020-04-08T17:15:00+05:30",
    "endTime" : "2020-04-08T18:00:00+05:30",
    "appointmentDate" : "2020-04-22T11:21:03.598Z",
    "patientId" : "5e9180990c74f8269a738acb",
    "doctorId" : "5e900b8c8c04d642df956d21",
    "userTimezone" : "Asia\/Kolkata",
    "reason" : "Test",
    "stripe_token" : {
    "object" : "token",
    "id" : "tok_1GagdxKaFUdU2GEAKZLmBMtj"
              }
}
*/
