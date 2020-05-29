package com.app.womba.model.postResponseModel

class LoginPost(
    email: String,
    password: String,
    userType: String,
    device_id: String,
    device_token: String,
    fcmtoken: String,
    device_type: String
) {
    var email = ""
    var password = ""
    var userType = ""
    var device_id = ""
    var device_token = ""
    var fcmtoken = ""
    var device_type = ""

    init {
        this.email = email
        this.password = password
        this.userType = userType
        this.device_id = device_id
        this.device_token = device_token
        this.fcmtoken = fcmtoken
        this.device_type = device_type
    }
}

class ForgetPost(
    email: String,
    userType: String
) {
    var email = ""
    var userType = ""
    init {
        this.email = email
        this.userType = userType
    }
}

class RegistrationPost(
    email: String,
    password: String,
    phone_number: String
) {
    var email = ""
    var password = ""
    var phone_number = ""

    init {
        this.email = email
        this.password = password
        this.phone_number = phone_number
    }
}

class ChangePassword(
    userId: String,
    oldPassword: String,
    newPassword: String
) {
    var userId = ""
    var oldPassword = ""
    var newPassword = ""

    init {
        this.userId = userId
        this.oldPassword = oldPassword
        this.newPassword = newPassword
    }
}