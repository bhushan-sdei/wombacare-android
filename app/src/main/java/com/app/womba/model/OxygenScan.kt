package com.evitalapp.model

import com.evitalapp.model.fullreport.Oxygen

data class OxygenScan(
    val oxygen: Oxygen?,
    val reportLogid: Int?,
    val reportdate: String?,
    val timestamp: String?
)