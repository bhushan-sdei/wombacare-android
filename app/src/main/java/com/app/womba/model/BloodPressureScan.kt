package com.app.womba.model

import com.evitalapp.model.fullreport.BloodPressure

data class BloodPressureScan(
    val bloodPressure: BloodPressure?,
    val reportLogid: Int?,
    val reportdate: String?,
    val timestamp: String?
)