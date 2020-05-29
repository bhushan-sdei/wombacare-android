package com.app.womba.model

import com.evitalapp.model.*

data class TrendData(
    val bloodPressureScanList: List<BloodPressureScan>?,
    val hrScanList: List<HrScan>?,
    val oxygenScanList: List<OxygenScan>?,
    val respirationScanList: List<RespirationScan>?,
    val stressScanList: List<StressScan>?,
    val hrvScanList: List<HrvScan>?,
    val _id: Int?
)