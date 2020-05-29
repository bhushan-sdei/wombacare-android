package com.app.womba.model

import com.app.womba.base.BaseModel

class VitalsTrendResponse : BaseModel {

    constructor(toInt: Int, message: String) : super(toInt, message)
    constructor(message: String?) : super(message!!)
    constructor()
    /**
     * code : 200
     * message : User E-vitals Data
     * data : {"_id":null,"stressScanList":[{"stress":{"value":384,"measurementUnit":"N.A."},"reportdate":"2020-05-08T14:54:37.309Z"}],"bloodPressureScanList":[{"bloodPressure":{"systolic":0,"diastolic":0},"reportdate":"2020-05-08T14:54:37.309Z"}],"respirationScanList":[{"respiration":{"value":12,"measurementUnit":"rpm"},"reportdate":"2020-05-08T14:54:37.309Z"}],"hrScanList":[{"hr":{"meanPulseRate":{"value":85,"measurementUnit":"bpm"}},"reportdate":"2020-05-08T14:54:37.309Z"}],"hrvScanList":[{"sdrr":{"value":18.51266954207822,"measurementUnit":"ms"},"reportdate":"2020-05-08T14:54:37.309Z"}],"oxygenScanList":[{"oxygen":{"saturationLevel":{"value":96,"measurementUnit":"%"}},"reportdate":"2020-05-08T14:54:37.309Z"}]}
     * responseStatus : 0
     * token :
     */
    var data: TrendData? = null
    var responseStatus = 0
    var token: String? = null

}