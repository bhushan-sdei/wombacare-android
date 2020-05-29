package com.app.womba.model.fullreport

import com.evitalapp.model.fullreport.*
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class Report {
    @SerializedName("hr")
    @Expose
    private var hr: Hr? = null

    @SerializedName("stress")
    @Expose
    private var stress: Stress? = null

    @SerializedName("respiration")
    @Expose
    private var respiration: Respiration? = null

    @SerializedName("oxygen")
    @Expose
    private var oxygen: Oxygen? = null

    @SerializedName("bloodPressure")
    @Expose
    private var bloodPressure: BloodPressure? = null

    fun getHr(): Hr? {
        return hr
    }

    fun setHr(hr: Hr?) {
        this.hr = hr
    }

    fun getStress(): Stress? {
        return stress
    }

    fun setStress(stress: Stress?) {
        this.stress = stress
    }

    fun getRespiration(): Respiration? {
        return respiration
    }

    fun setRespiration(respiration: Respiration?) {
        this.respiration = respiration
    }

    fun getOxygen(): Oxygen? {
        return oxygen
    }

    fun setOxygen(oxygen: Oxygen?) {
        this.oxygen = oxygen
    }

    fun getBloodPressure(): BloodPressure? {
        return bloodPressure
    }

    fun setBloodPressure(bloodPressure: BloodPressure?) {
        this.bloodPressure = bloodPressure
    }

}