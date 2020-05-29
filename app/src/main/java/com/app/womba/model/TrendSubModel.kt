package com.app.womba.model

import com.app.womba.base.BaseModel
import com.app.womba.model.TrendData

class TrendSubModel: BaseModel {

    constructor(toInt: Int, message: String) : super(toInt, message)
    constructor(message: String?) : super(message!!)
    constructor()
//    val `data`: TrendData?=null



}