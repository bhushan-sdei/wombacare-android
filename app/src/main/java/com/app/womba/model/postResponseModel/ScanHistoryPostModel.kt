package com.app.womba.model.postResponseModel

data class ScanHistoryPostModel(
    val patientId: String,
    val page: Int,
    val limit: Int
)