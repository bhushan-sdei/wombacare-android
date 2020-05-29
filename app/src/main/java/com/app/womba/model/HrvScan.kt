package com.evitalapp.model

import com.app.womba.model.fullreport.Sdrr

data class HrvScan(
    val reportLogid: Int?,
    val reportdate: String?,
    val sdrr: Sdrr?,
    val timestamp: String?
)