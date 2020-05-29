package com.evitalapp.model

import com.evitalapp.model.fullreport.Respiration

data class RespirationScan(
    val reportLogid: Int?,
    val reportdate: String?,
    val respiration: Respiration?,
    val timestamp: String?
)