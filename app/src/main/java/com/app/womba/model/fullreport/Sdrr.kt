package com.app.womba.model.fullreport

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Sdrr {
    @SerializedName("value")
    @Expose
    private var value: Double? = null

    @SerializedName("measurementUnit")
    @Expose
    private var measurementUnit: String? = null

    fun getValue(): Double? {
        return value
    }

    fun setValue(value: Double?) {
        this.value = value
    }

    fun getMeasurementUnit(): String? {
        return measurementUnit
    }

    fun setMeasurementUnit(measurementUnit: String?) {
        this.measurementUnit = measurementUnit
    }

}