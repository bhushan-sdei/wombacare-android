package com.app.womba.base

import com.app.womba.utils.CODE_ERROR
import com.google.gson.annotations.SerializedName

open class BaseModel {
    /**
     * statusCode : 200
     * message : Registration successful
     */

    var code = CODE_ERROR
    var message: String = ""

    constructor() {
        this.code = CODE_ERROR
        this.message = ""
    }

    constructor(message: String) {
        this.message = message
    }

    constructor(code: Int, message: String){
        this.message = message
        this.code = code
    }
}
