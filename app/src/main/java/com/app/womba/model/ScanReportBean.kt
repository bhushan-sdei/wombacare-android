package com.app.womba.model

import com.evitalapp.model.fullreport.*


class ScanReportBean {
    /**
     * hr : {"meanPulseRate":{"value":85,"measurementUnit":"bpm"}}
     * stress : {"value":384,"measurementUnit":"N.A."}
     * respiration : {"value":12,"measurementUnit":"rpm"}
     * bloodPressure : {"systolic":0,"diastolic":0}
     * TimeParameters : {"meanRRInterval":{"value":709.8022369595227,"measurementUnit":"ms"},"rmssd":{"value":12.843227707013515,"measurementUnit":"ms"},"lnRmssd":{"value":2.5528166457222925,"measurementUnit":"ms"},"sdrr":{"value":18.51266954207822,"measurementUnit":"ms"},"pRr50":{"value":1.8867924528301887,"measurementUnit":"%"},"rr50":{"value":3,"measurementUnit":"N.A."},"amo50ms":{"value":71.875,"measurementUnit":"%"},"mxDmn":{"value":129.03997349256713,"measurementUnit":"ms"},"si":{"value":384,"measurementUnit":"N.A."}}
     * FrequencyParameters : {"totalPower":{"value":238.2999025233554,"measurementUnit":"ms2"},"vlfPower":{"value":133.68176305217253,"measurementUnit":"ms2"},"lfPower":{"value":58.6529424263847,"measurementUnit":"ms2"},"hfPower":{"value":45.96519704479816,"measurementUnit":"ms2"},"lfToHf":{"value":1.2760293917422116,"measurementUnit":"ms2"}}
     */
    var hr: Hr? = null
    var stress: Stress? = null
    var respiration: Respiration? = null
    var bloodPressure: BloodPressure? = null
    var TimeParameters: TimeParameters? = null
    var frequencyParameters: FrequencyParameters? = null

}

