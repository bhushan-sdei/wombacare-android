package com.evitalapp.model.fullreport

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Hr {
    @SerializedName("meanPulseRate")
    @Expose
    private var meanPulseRate: MeanPulseRate? = null

    fun getMeanPulseRate(): MeanPulseRate? {
        return meanPulseRate
    }

    fun setMeanPulseRate(meanPulseRate: MeanPulseRate?) {
        this.meanPulseRate = meanPulseRate
    }
}