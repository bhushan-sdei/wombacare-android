package com.evitalapp.model

import com.evitalapp.model.fullreport.Hr

data class HrScan(
    val hr: Hr?,
    val reportLogid: Int?,
    val reportdate: String?,
    val timestamp: String?
)