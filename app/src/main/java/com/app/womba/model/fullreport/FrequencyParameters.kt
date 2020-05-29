package com.evitalapp.model.fullreport

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class FrequencyParameters {
    @SerializedName("totalPower")
    @Expose
    private var totalPower: TotalPower? = null

    @SerializedName("vlfPower")
    @Expose
    private var vlfPower: VlfPower? = null

    @SerializedName("lfPower")
    @Expose
    private var lfPower: LfPower? = null

    @SerializedName("hfPower")
    @Expose
    private var hfPower: HfPower? = null

    @SerializedName("lfToHf")
    @Expose
    private var lfToHf: LfToHf? = null

    fun getTotalPower(): TotalPower? {
        return totalPower
    }

    fun setTotalPower(totalPower: TotalPower?) {
        this.totalPower = totalPower
    }

    fun getVlfPower(): VlfPower? {
        return vlfPower
    }

    fun setVlfPower(vlfPower: VlfPower?) {
        this.vlfPower = vlfPower
    }

    fun getLfPower(): LfPower? {
        return lfPower
    }

    fun setLfPower(lfPower: LfPower?) {
        this.lfPower = lfPower
    }

    fun getHfPower(): HfPower? {
        return hfPower
    }

    fun setHfPower(hfPower: HfPower?) {
        this.hfPower = hfPower
    }

    fun getLfToHf(): LfToHf? {
        return lfToHf
    }

    fun setLfToHf(lfToHf: LfToHf?) {
        this.lfToHf = lfToHf
    }
}