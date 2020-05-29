package com.evitalapp.model.fullreport

import com.app.womba.model.fullreport.Sdrr
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class TimeParameters {
    @SerializedName("meanRRInterval")
    @Expose
    private var meanRRInterval: MeanRRInterval? = null

    @SerializedName("rmssd")
    @Expose
    private var rmssd: Rmssd? = null

    @SerializedName("lnRmssd")
    @Expose
    private var lnRmssd: LnRmssd? = null

    @SerializedName("sdrr")
    @Expose
    private var sdrr: Sdrr? = null

    @SerializedName("pRr50")
    @Expose
    private var pRr50: PRr50? = null

    @SerializedName("rr50")
    @Expose
    private var rr50: Rr50? = null

    @SerializedName("amo50ms")
    @Expose
    private var amo50ms: Amo50ms? = null

    @SerializedName("mxDmn")
    @Expose
    private var mxDmn: MxDmn? = null

    @SerializedName("si")
    @Expose
    private var si: Si? = null

    fun getMeanRRInterval(): MeanRRInterval? {
        return meanRRInterval
    }

    fun setMeanRRInterval(meanRRInterval: MeanRRInterval?) {
        this.meanRRInterval = meanRRInterval
    }

    fun getRmssd(): Rmssd? {
        return rmssd
    }

    fun setRmssd(rmssd: Rmssd?) {
        this.rmssd = rmssd
    }

    fun getLnRmssd(): LnRmssd? {
        return lnRmssd
    }

    fun setLnRmssd(lnRmssd: LnRmssd?) {
        this.lnRmssd = lnRmssd
    }

    fun getSdrr(): Sdrr? {
        return sdrr
    }

    fun setSdrr(sdrr: Sdrr?) {
        this.sdrr = sdrr
    }

    fun getPRr50(): PRr50? {
        return pRr50
    }

    fun setPRr50(pRr50: PRr50?) {
        this.pRr50 = pRr50
    }

    fun getRr50(): Rr50? {
        return rr50
    }

    fun setRr50(rr50: Rr50?) {
        this.rr50 = rr50
    }

    fun getAmo50ms(): Amo50ms? {
        return amo50ms
    }

    fun setAmo50ms(amo50ms: Amo50ms?) {
        this.amo50ms = amo50ms
    }

    fun getMxDmn(): MxDmn? {
        return mxDmn
    }

    fun setMxDmn(mxDmn: MxDmn?) {
        this.mxDmn = mxDmn
    }

    fun getSi(): Si? {
        return si
    }

    fun setSi(si: Si?) {
        this.si = si
    }
}