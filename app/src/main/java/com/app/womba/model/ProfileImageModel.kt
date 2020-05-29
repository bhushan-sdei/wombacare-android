package com.app.womba.model

import com.app.womba.base.BaseModel

class ProfileImageModel: BaseModel {
    /**
     * code : 200
     * message : Image updated successfully
     * imageData : http://54.190.192.105:5010//images/patient/5eb7b594311f79072bbb9d84_1589107430082.png
     */
    constructor(toInt: Int, message: String) : super(toInt, message)
    constructor(message: String?) : super(message!!)
    constructor()

    var imageData: String? = null

}