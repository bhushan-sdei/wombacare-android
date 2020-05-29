package com.app.womba.model.fullreport

import com.app.womba.model.ScanReportBean
import com.evitalapp.model.fullreport.FullData
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class HealthModel {
    @SerializedName("timestamp")
    @Expose
    private var timestamp: String? = null

    @SerializedName("patientId")
    @Expose
    private var patientId: String? = null

    @SerializedName("Report")
    @Expose
    private var report: Report? = null

    @SerializedName("FullData")
    @Expose
    private var fullData: FullData? = null

    @SerializedName("report")
    @Expose
    private var report_new: Report? = null

    @SerializedName("scanReport")
    @Expose
    private var scanReport: ScanReportBean? = null


    fun getTimestamp(): String? {
        return timestamp
    }

    fun setTimestamp(timestamp: String?) {
        this.timestamp = timestamp
    }

    fun getPatientId(): String? {
        return patientId
    }

    fun setPatientId(patientId: String?) {
        this.patientId = patientId
    }

    fun getReport(): Report? {
        return report
    }

    fun setReport(report: Report?) {
        this.report = report
    }

    fun getFullData(): FullData? {
        return fullData
    }

    fun setFullData(fullData: FullData?) {
        this.fullData = fullData
    }


    fun setReportNew(reportNew: Report?) {
        this.report_new = reportNew
    }

    fun getReportNew(): Report? {
        return report_new
    }

    fun getScanReport(): ScanReportBean? {
        return scanReport
    }

    fun setScanReport(scanReport: ScanReportBean?) {
        this.scanReport = scanReport
    }


}