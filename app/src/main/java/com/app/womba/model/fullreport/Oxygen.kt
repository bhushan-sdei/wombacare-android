package com.evitalapp.model.fullreport

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Oxygen {
    @SerializedName("saturationLevel")
    @Expose
    private var saturationLevel: SaturationLevel? = null

    fun getSaturationLevel(): SaturationLevel? {
        return saturationLevel
    }

    fun setSaturationLevel(saturationLevel: SaturationLevel?) {
        this.saturationLevel = saturationLevel
    }
}