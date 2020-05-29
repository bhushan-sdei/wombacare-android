package com.evitalapp.model

import com.evitalapp.model.fullreport.Stress

data class StressScan(
    val reportLogid: Int?,
    val reportdate: String?,
    val stress: Stress?,
    val timestamp: String?
)