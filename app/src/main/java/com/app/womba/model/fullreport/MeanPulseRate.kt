package com.evitalapp.model.fullreport

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class MeanPulseRate {
    @SerializedName("value")
    @Expose
    private var value: Int? = null

    @SerializedName("measurementUnit")
    @Expose
    private var measurementUnit: String? = null

    fun getValue(): Int? {
        return value
    }

    fun setValue(value: Int?) {
        this.value = value
    }

    fun getMeasurementUnit(): String? {
        return measurementUnit
    }

    fun setMeasurementUnit(measurementUnit: String?) {
        this.measurementUnit = measurementUnit
    }
}