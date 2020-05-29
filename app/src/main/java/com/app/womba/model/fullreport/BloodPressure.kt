package com.evitalapp.model.fullreport

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class BloodPressure {
    @SerializedName("systolic")
    @Expose
    private var systolic: Int? = null

    @SerializedName("diastolic")
    @Expose
    private var diastolic: Int? = null

    fun getSystolic(): Int? {
        return systolic
    }

    fun setSystolic(systolic: Int?) {
        this.systolic = systolic
    }

    fun getDiastolic(): Int? {
        return diastolic
    }

    fun setDiastolic(diastolic: Int?) {
        this.diastolic = diastolic
    }

}