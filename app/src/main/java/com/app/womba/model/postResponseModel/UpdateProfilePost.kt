package com.app.womba.model.postResponseModel

//Request Params: {
//    "gender" : "MALE",
//    "bmi" : "18.87",
//    "current_lng" : "",
//    "current_lat" : "",
//    "patientId" : "5e9180990c74f8269a738acb",
//    "zip" : "00445",
//    "country" : "United States",
//    "heightFt" : "4",
//    "ethnicity" : "Social worker",
//    "bloodGroup" : "A+",
//    "lastname" : "test",
//    "address" : "1314  Bicetown Road",
//    "city" : "New York",
//    "statename" : "New York",
//    "weight" : "155.0",
//    "language" : "5d9ee78b1070171562482cae",
//    "dob" : "04\/05\/2011",
//    "heightInch" : "2",
//    "maritial_status" : "",
//    "firstname" : " Grant",
//    "phone_number" : "7507469605"
//}

class UpdateProfilePost(
    var patientId: String,
    var firstname: String,
    var lastname: String,
    var gender: String,
    var dob: String,
    var heightFt: String,
    var heightInch: String,
    var weight: String,
    var bloodGroup: String,
    var ethnicity: String,
    var address: String,
    var country: String,
    var city: String,
    var statename: String,
    var zip: String,
    var bmi: String,
    var current_lng: String,
    var current_lat: String,
    var language: String,
    var maritial_status: String,
    var phone_number: String
)

class PatientId(
    var patientId: String
)
class Notification(
    var userId: String
)

class GenerateToken(
//    "userId" : "5e9180990c74f8269a738acb",
//    "appointmentId" : "5ea028bce1d55f3d2ac0a48a"

    var userId: String,
    var appointmentId: String
)
