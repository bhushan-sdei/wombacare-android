package com.app.womba.model

import com.app.womba.base.BaseModel

class UserModel :BaseModel{
    /**
     * code : 200
     * message : Client login successful
     * data : {"_id":"5e8cd3f9c51b14166d847480","username":"","email":"shubham.verma740@gmail.com","phone_number":"4fd785c977292a216719b155a6","profile_pic":"noUser.jpg","experience":"","expMonth":"","address":"","city":"","state":[],"zip":"","country":"","token":"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVlOGNkM2Y5YzUxYjE0MTY2ZDg0NzQ4MCIsImlhdCI6MTU4NjI4NzcyMSwiZXhwIjoxNTg2MzE2NTIxfQ.6zKsn4fLYj_NLmyL6gwyOedCRq-UGEn7wvj_TQ1inoE","device_token":"cueiylqYOXk:APA91bGq5O05UQrKYYPELSfFOzxDOvBJjUhIBseRXTFPHMg86uDOVnp_rWT8SmAevcrqRDCYbTOywPcpj9quFiUFkokL8ZogFkNCWx6CuJ_unJ_JJowXhLfaHmzWW2G-k4NBxEugBH5w","device_type":"Android","device_id":"d2f058af4aea0abe","gender":"","dob":"","current_lat":0,"current_lng":0,"status":true,"deleted":false,"isVerified":false,"LicenseNum":"","is_email_verified":false,"is_phone_verified":false,"userType":"Patient","role":"Patient","height_feet":"","height_inch":"","weight":"","BMI":"","blood_group":"","street":"","doctor_qualification":"","phoneNumberPrefix":"+","lifestyleQuestionInfoId":"","lifestyleAnswerInfoId":"","patientLifeStyleInfoId":"5e8cd3f9c51b14166d847481","patientContactInfoId":"5e8cd3f9c51b14166d847483","patientMedicalInfoId":"5e8cd3f9c51b14166d847482","doctorSpecalityId":[],"websitelink":"","formlink":"","userTimezone":"","fee":"","biography":"","fcmtoken":"","stripeStatus":0,"stripeAccountId":"","pdfgenratedDate":"start","terms":false,"overage":false,"familyId":"","languageId":[],"available":false,"isQuestionnaireFilled":false,"employerId":"","consetFilled":false,"ethnicity":"","about":"","updateProfileStatus":false,"firstname":"","lastname":"","activation_key":" ","password":"55dc87c474","joinDate":"2020-04-07T19:26:49.205Z","awards":[],"createdAt":"2020-04-07T19:26:49.218Z","updatedAt":"2020-04-07T19:28:41.887Z","__v":0}
     */
    var data: DataBean? = null

    constructor(toInt: Int, message: String) : super(toInt, message)

    constructor(message: String?) : super(message!!)

    constructor()

    class DataBean {
        /**
         * _id : 5eb7b4cc311f79072bbb9d7d
         * location : {"type":"Point","coordinates":[0,0]}
         * username : shuba sdf
         * email : sf@yopmail.com
         * phone_number : 7507469605
         * profile_pic : noUser.jpg
         * experience :
         * expMonth :
         * address : asdf
         * city : sadf
         * statename :
         * province : []
         * zip : 453453
         * country : asdf
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVlYjdiNGNjMzExZjc5MDcyYmJiOWQ3ZCIsImlhdCI6MTU4OTA5NzcyNywiZXhwIjoxNTg5MTI2NTI3fQ.oC6NcQaIB-bkBybGQ8ibteL9cwBHwkkcjVGqcvlkqfc
         * device_token :
         * device_type : Android
         * device_id : 1b7647b2da091e7
         * gender : Female
         * dob : 2002-01-10T00:00:00-05:00
         * current_lat : 0
         * current_lng : 0
         * status : true
         * deleted : false
         * isVerified : true
         * LicenseNum :
         * is_email_verified : false
         * is_phone_verified : false
         * userType : Patient
         * role : Patient
         * height_feet : 4
         * height_inch : 6
         * weight : 143
         * BMI : 34
         * blood_group : B-
         * street :
         * doctor_qualification :
         * phoneNumberPrefix : ""
         * lifestyleQuestionInfoId : ""
         * lifestyleAnswerInfoId : ""
         * patientLifeStyleInfoId : 5eb7b4cc311f79072bbb9d7e
         * patientContactInfoId : 5eb7b4cc311f79072bbb9d80
         * patientMedicalInfoId : 5eb7b4cc311f79072bbb9d7f
         * doctorSpecalityId : []
         * websitelink :
         * formlink :
         * userTimezone :
         * fee :
         * biography :
         * fcmtoken :
         * stripeStatus : 0
         * stripeAccountId :
         * pdfgenratedDate : start
         * terms : false
         * overage : false
         * familyId : ""
         * languageId : ["5da5c35445efa43461398505"]
         * available : false
         * isQuestionnaireFilled : false
         * employerId : ""
         * consetFilled : false
         * ethnicity : American
         * about :
         * updateProfileStatus : false
         * contactNumber :
         * services : []
         * race :
         * personalGreeting :
         * maritalStatus :
         * npiNo : ""
         * firstname : shuba
         * lastname : sdf
         * activation_key :
         * joinDate : 2020-05-10T08:01:16.921Z
         * awards : []
         * educations : []
         * experiences : []
         * createdAt : 2020-05-10T08:01:16.930Z
         * updatedAt : 2020-05-10T08:54:36.945Z
         * __v : 0
         * language :
         * state :
         */
        private var _id: String? = ""
        var location: LocationBean? = null
        var username: String? = ""
        var email: String? = ""
        var phone_number: String? = ""
        var profile_pic: String? = ""
        var experience: String? = ""
        var expMonth: String? = ""
        var address: String? = ""
        var city: String? = ""
        var statename: String? = ""
        var zip: String? = ""
        var country: String? = ""
        var token: String? = ""
        var device_token: String? = ""
        var device_type: String? = ""
        var device_id: String? = ""
        var gender: String? = ""
        var dob: String? = ""
        var current_lat = 0
        var current_lng = 0
        var isStatus = false
        var isDeleted = false
        var isIsVerified = false
            private set
        var licenseNum: String? = ""
        var isIs_email_verified = false
            private set
        var isIs_phone_verified = false
            private set
        var userType: String? = ""
        var role: String? = ""
        var height_feet: String? = ""
        var height_inch: String? = ""
        var weight: String? = ""
        var bMI: String? = ""
        var blood_group: String? = ""
        var street: String? = ""
        var doctor_qualification: String? = ""
        var phoneNumberPrefix: Any? = ""
        var lifestyleQuestionInfoId: Any? = ""
        var lifestyleAnswerInfoId: Any? = ""
        var patientLifeStyleInfoId: String? = ""
        var patientContactInfoId: String? = ""
        var patientMedicalInfoId: String? = ""
        var websitelink: String? = ""
        var formlink: String? = ""
        var userTimezone: String? = ""
        var fee: String? = ""
        var biography: String? = ""
        var fcmtoken: String? = ""
        var stripeStatus = 0
        var stripeAccountId: String? = ""
        var pdfgenratedDate: String? = ""
        var isTerms = false
        var isOverage = false
        var familyId: Any? = ""
        var isAvailable = false
        var isIsQuestionnaireFilled = false
            private set
        var employerId: Any? = ""
        var isConsetFilled = false
        var ethnicity: String? = ""
        var about: String? = ""
        var isUpdateProfileStatus = false
        var contactNumber: String? = ""
        var race: String? = ""
        var personalGreeting: String? = ""
        var maritalStatus: String? = ""
        var npiNo: Any? = ""
        var firstname: String? = ""
        var lastname: String? = ""
        var activation_key: String? = ""
        var joinDate: String? = ""
        var createdAt: String? = ""
        var updatedAt: String? = ""
        private var __v = 0
        var language: String? = ""
        var state: String? = ""
        var province: List<*>? =null
        var doctorSpecalityId: List<*>? = null
        var languageId: List<String>? =null
        var services: List<*>? = null
        var awards: List<*>? = null
        var educations: List<*>? =null
        var experiences: List<*>? = null
        fun get_id(): String? {
            return _id
        }

        fun set_id(_id: String?) {
            this._id = _id
        }

        fun setIsVerified(isVerified: Boolean) {
            isIsVerified = isVerified
        }

        fun setIs_email_verified(is_email_verified: Boolean) {
            isIs_email_verified = is_email_verified
        }

        fun setIs_phone_verified(is_phone_verified: Boolean) {
            isIs_phone_verified = is_phone_verified
        }

        fun setIsQuestionnaireFilled(isQuestionnaireFilled: Boolean) {
            isIsQuestionnaireFilled = isQuestionnaireFilled
        }

        fun get__v(): Int {
            return __v
        }

        fun set__v(__v: Int) {
            this.__v = __v
        }

        class LocationBean {
            /**
             * type : Point
             * coordinates : [0,0]
             */
            var type: String? = ""
            var coordinates: List<Int>? = null

        }
    }
}