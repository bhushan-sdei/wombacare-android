package com.app.womba.model

import com.app.womba.base.BaseModel
import com.app.womba.model.fullreport.HealthModel
import com.app.womba.model.fullreport.Report

class VitalHistoryData: BaseModel {
    /**
     * code : 200
     * message : User vitals data
     * data : {"bindata":[{"_id":"08-05-2020","reportDate":"08-05-2020","scanDataList":[{"timeScan":"14:54:32","report":{"hr":{"meanPulseRate":{"value":85,"measurementUnit":"bpm"}},"stress":{"value":384,"measurementUnit":"N.A."},"respiration":{"value":12,"measurementUnit":"rpm"},"oxygen":{"saturationLevel":{"value":96,"measurementUnit":"%"}},"bloodPressure":{"systolic":0,"diastolic":0}},"scanReport":{"hr":{"meanPulseRate":{"value":85,"measurementUnit":"bpm"}},"stress":{"value":384,"measurementUnit":"N.A."},"respiration":{"value":12,"measurementUnit":"rpm"},"bloodPressure":{"systolic":0,"diastolic":0},"TimeParameters":{"meanRRInterval":{"value":709.8022369595227,"measurementUnit":"ms"},"rmssd":{"value":12.843227707013515,"measurementUnit":"ms"},"lnRmssd":{"value":2.5528166457222925,"measurementUnit":"ms"},"sdrr":{"value":18.51266954207822,"measurementUnit":"ms"},"pRr50":{"value":1.8867924528301887,"measurementUnit":"%"},"rr50":{"value":3,"measurementUnit":"N.A."},"amo50ms":{"value":71.875,"measurementUnit":"%"},"mxDmn":{"value":129.03997349256713,"measurementUnit":"ms"},"si":{"value":384,"measurementUnit":"N.A."}},"FrequencyParameters":{"totalPower":{"value":238.2999025233554,"measurementUnit":"ms2"},"vlfPower":{"value":133.68176305217253,"measurementUnit":"ms2"},"lfPower":{"value":58.6529424263847,"measurementUnit":"ms2"},"hfPower":{"value":45.96519704479816,"measurementUnit":"ms2"},"lfToHf":{"value":1.2760293917422116,"measurementUnit":"ms2"}}}}]},{"_id":"09-05-2020","reportDate":"09-05-2020","scanDataList":[{"timeScan":"04:52:06","report":{"hr":{"meanPulseRate":{"value":97,"measurementUnit":"bpm"}},"stress":{"value":213,"measurementUnit":"N.A."},"respiration":{"value":11,"measurementUnit":"rpm"},"oxygen":{"saturationLevel":{"value":91,"measurementUnit":"%"}},"bloodPressure":{"systolic":0,"diastolic":0}},"scanReport":{"hr":{"meanPulseRate":{"value":97,"measurementUnit":"bpm"}},"stress":{"value":213,"measurementUnit":"N.A."},"respiration":{"value":11,"measurementUnit":"rpm"},"bloodPressure":{"systolic":0,"diastolic":0},"TimeParameters":{"meanRRInterval":{"value":617.1094918724928,"measurementUnit":"ms"},"rmssd":{"value":28.59490907036988,"measurementUnit":"ms"},"lnRmssd":{"value":3.353228697448598,"measurementUnit":"ms"},"sdrr":{"value":37.431389314276174,"measurementUnit":"ms"},"pRr50":{"value":9.473684210526317,"measurementUnit":"%"},"rr50":{"value":9,"measurementUnit":"N.A."},"amo50ms":{"value":52.083333333333336,"measurementUnit":"%"},"mxDmn":{"value":195.60945232379078,"measurementUnit":"ms"},"si":{"value":213,"measurementUnit":"N.A."}},"FrequencyParameters":{"totalPower":{"value":1464.555681078499,"measurementUnit":"ms2"},"vlfPower":{"value":221.7714482764806,"measurementUnit":"ms2"},"lfPower":{"value":633.0153581547048,"measurementUnit":"ms2"},"hfPower":{"value":609.7688746473136,"measurementUnit":"ms2"},"lfToHf":{"value":1.0381234340976109,"measurementUnit":"ms2"}}}}]}],"totalCount":[{"count":2}]}
     * responseStatus : 0
     * token :
     */
    constructor(toInt: Int, message: String) : super(toInt, message)
    constructor(message: String?) : super(message!!)
    constructor()

    var data: DataBean? = null
    var responseStatus = 0
    var token: String? = null

    class DataBean {
        var bindata: List<BindataBean>? = null
        var totalCount: List<TotalCountBean>? = null

        class BindataBean {
            /**
             * _id : 08-05-2020
             * reportDate : 08-05-2020
             * scanDataList : [{"timeScan":"14:54:32","report":{"hr":{"meanPulseRate":{"value":85,"measurementUnit":"bpm"}},"stress":{"value":384,"measurementUnit":"N.A."},"respiration":{"value":12,"measurementUnit":"rpm"},"oxygen":{"saturationLevel":{"value":96,"measurementUnit":"%"}},"bloodPressure":{"systolic":0,"diastolic":0}},"scanReport":{"hr":{"meanPulseRate":{"value":85,"measurementUnit":"bpm"}},"stress":{"value":384,"measurementUnit":"N.A."},"respiration":{"value":12,"measurementUnit":"rpm"},"bloodPressure":{"systolic":0,"diastolic":0},"TimeParameters":{"meanRRInterval":{"value":709.8022369595227,"measurementUnit":"ms"},"rmssd":{"value":12.843227707013515,"measurementUnit":"ms"},"lnRmssd":{"value":2.5528166457222925,"measurementUnit":"ms"},"sdrr":{"value":18.51266954207822,"measurementUnit":"ms"},"pRr50":{"value":1.8867924528301887,"measurementUnit":"%"},"rr50":{"value":3,"measurementUnit":"N.A."},"amo50ms":{"value":71.875,"measurementUnit":"%"},"mxDmn":{"value":129.03997349256713,"measurementUnit":"ms"},"si":{"value":384,"measurementUnit":"N.A."}},"FrequencyParameters":{"totalPower":{"value":238.2999025233554,"measurementUnit":"ms2"},"vlfPower":{"value":133.68176305217253,"measurementUnit":"ms2"},"lfPower":{"value":58.6529424263847,"measurementUnit":"ms2"},"hfPower":{"value":45.96519704479816,"measurementUnit":"ms2"},"lfToHf":{"value":1.2760293917422116,"measurementUnit":"ms2"}}}}]
             */
            private var _id: String? = null
            var reportDate: String? = null
            var scanDataList: List<DataX>? = null
            fun get_id(): String? {
                return _id
            }

            fun set_id(_id: String?) {
                this._id = _id
            }

        }

        class TotalCountBean {
            /**
             * count : 2
             */
            var count = 0

        }


        class DataX {
            /**
             * timeScan : 14:54:32
             * report : {"hr":{"meanPulseRate":{"value":85,"measurementUnit":"bpm"}},"stress":{"value":384,"measurementUnit":"N.A."},"respiration":{"value":12,"measurementUnit":"rpm"},"oxygen":{"saturationLevel":{"value":96,"measurementUnit":"%"}},"bloodPressure":{"systolic":0,"diastolic":0}}
             * scanReport : {"hr":{"meanPulseRate":{"value":85,"measurementUnit":"bpm"}},"stress":{"value":384,"measurementUnit":"N.A."},"respiration":{"value":12,"measurementUnit":"rpm"},"bloodPressure":{"systolic":0,"diastolic":0},"TimeParameters":{"meanRRInterval":{"value":709.8022369595227,"measurementUnit":"ms"},"rmssd":{"value":12.843227707013515,"measurementUnit":"ms"},"lnRmssd":{"value":2.5528166457222925,"measurementUnit":"ms"},"sdrr":{"value":18.51266954207822,"measurementUnit":"ms"},"pRr50":{"value":1.8867924528301887,"measurementUnit":"%"},"rr50":{"value":3,"measurementUnit":"N.A."},"amo50ms":{"value":71.875,"measurementUnit":"%"},"mxDmn":{"value":129.03997349256713,"measurementUnit":"ms"},"si":{"value":384,"measurementUnit":"N.A."}},"FrequencyParameters":{"totalPower":{"value":238.2999025233554,"measurementUnit":"ms2"},"vlfPower":{"value":133.68176305217253,"measurementUnit":"ms2"},"lfPower":{"value":58.6529424263847,"measurementUnit":"ms2"},"hfPower":{"value":45.96519704479816,"measurementUnit":"ms2"},"lfToHf":{"value":1.2760293917422116,"measurementUnit":"ms2"}}}
             */
            var timeScan: String? = null
            var report: Report? = null
            var scanReport: ScanReportBean? = null

//            class ReportBean {
//                /**
//                 * hr : {"meanPulseRate":{"value":85,"measurementUnit":"bpm"}}
//                 * stress : {"value":384,"measurementUnit":"N.A."}
//                 * respiration : {"value":12,"measurementUnit":"rpm"}
//                 * oxygen : {"saturationLevel":{"value":96,"measurementUnit":"%"}}
//                 * bloodPressure : {"systolic":0,"diastolic":0}
//                 */
//                var hr: HrBean? = null
//                var stress: StressBean? = null
//                var respiration: RespirationBean? = null
//                var oxygen: OxygenBean? = null
//                var bloodPressure: BloodPressureBean? = null
//
//                class HrBean {
//                    /**
//                     * meanPulseRate : {"value":85,"measurementUnit":"bpm"}
//                     */
//                    var meanPulseRate: MeanPulseRateBean? = null
//
//                    class MeanPulseRateBean {
//                        /**
//                         * value : 85
//                         * measurementUnit : bpm
//                         */
//                        var value = 0
//                        var measurementUnit: String? = null
//
//                    }
//                }
//
//                class StressBean {
//                    /**
//                     * value : 384
//                     * measurementUnit : N.A.
//                     */
//                    var value = 0
//                    var measurementUnit: String? = null
//
//                }
//
//                class RespirationBean {
//                    /**
//                     * value : 12
//                     * measurementUnit : rpm
//                     */
//                    var value = 0
//                    var measurementUnit: String? = null
//
//                }
//
//                class OxygenBean {
//                    /**
//                     * saturationLevel : {"value":96,"measurementUnit":"%"}
//                     */
//                    var saturationLevel: SaturationLevelBean? = null
//
//                    class SaturationLevelBean {
//                        /**
//                         * value : 96
//                         * measurementUnit : %
//                         */
//                        var value = 0
//                        var measurementUnit: String? = null
//
//                    }
//                }
//
//                class BloodPressureBean {
//                    /**
//                     * systolic : 0
//                     * diastolic : 0
//                     */
//                    var systolic = 0
//                    var diastolic = 0
//
//                }
//            }

//            class ScanReportBean {
//                /**
//                 * hr : {"meanPulseRate":{"value":85,"measurementUnit":"bpm"}}
//                 * stress : {"value":384,"measurementUnit":"N.A."}
//                 * respiration : {"value":12,"measurementUnit":"rpm"}
//                 * bloodPressure : {"systolic":0,"diastolic":0}
//                 * TimeParameters : {"meanRRInterval":{"value":709.8022369595227,"measurementUnit":"ms"},"rmssd":{"value":12.843227707013515,"measurementUnit":"ms"},"lnRmssd":{"value":2.5528166457222925,"measurementUnit":"ms"},"sdrr":{"value":18.51266954207822,"measurementUnit":"ms"},"pRr50":{"value":1.8867924528301887,"measurementUnit":"%"},"rr50":{"value":3,"measurementUnit":"N.A."},"amo50ms":{"value":71.875,"measurementUnit":"%"},"mxDmn":{"value":129.03997349256713,"measurementUnit":"ms"},"si":{"value":384,"measurementUnit":"N.A."}}
//                 * FrequencyParameters : {"totalPower":{"value":238.2999025233554,"measurementUnit":"ms2"},"vlfPower":{"value":133.68176305217253,"measurementUnit":"ms2"},"lfPower":{"value":58.6529424263847,"measurementUnit":"ms2"},"hfPower":{"value":45.96519704479816,"measurementUnit":"ms2"},"lfToHf":{"value":1.2760293917422116,"measurementUnit":"ms2"}}
//                 */
//                var hr: HrBeanX? = null
//                var stress: StressBeanX? = null
//                var respiration: RespirationBeanX? = null
//                var bloodPressure: BloodPressureBeanX? = null
//                var timeParameters: TimeParametersBean? = null
//                var frequencyParameters: FrequencyParametersBean? = null
//
//                class HrBeanX {
//                    /**
//                     * meanPulseRate : {"value":85,"measurementUnit":"bpm"}
//                     */
//                    var meanPulseRate: MeanPulseRateBeanX? = null
//
//                    class MeanPulseRateBeanX {
//                        /**
//                         * value : 85
//                         * measurementUnit : bpm
//                         */
//                        var value = 0
//                        var measurementUnit: String? = null
//
//                    }
//                }
//
//                class StressBeanX {
//                    /**
//                     * value : 384
//                     * measurementUnit : N.A.
//                     */
//                    var value = 0
//                    var measurementUnit: String? = null
//
//                }
//
//                class RespirationBeanX {
//                    /**
//                     * value : 12
//                     * measurementUnit : rpm
//                     */
//                    var value = 0
//                    var measurementUnit: String? = null
//
//                }
//
//                class BloodPressureBeanX {
//                    /**
//                     * systolic : 0
//                     * diastolic : 0
//                     */
//                    var systolic = 0
//                    var diastolic = 0
//
//                }
//
//                class TimeParametersBean {
//                    /**
//                     * meanRRInterval : {"value":709.8022369595227,"measurementUnit":"ms"}
//                     * rmssd : {"value":12.843227707013515,"measurementUnit":"ms"}
//                     * lnRmssd : {"value":2.5528166457222925,"measurementUnit":"ms"}
//                     * sdrr : {"value":18.51266954207822,"measurementUnit":"ms"}
//                     * pRr50 : {"value":1.8867924528301887,"measurementUnit":"%"}
//                     * rr50 : {"value":3,"measurementUnit":"N.A."}
//                     * amo50ms : {"value":71.875,"measurementUnit":"%"}
//                     * mxDmn : {"value":129.03997349256713,"measurementUnit":"ms"}
//                     * si : {"value":384,"measurementUnit":"N.A."}
//                     */
//                    var meanRRInterval: MeanRRIntervalBean? = null
//                    var rmssd: RmssdBean? = null
//                    var lnRmssd: LnRmssdBean? = null
//                    var sdrr: SdrrBean? = null
//                    var pRr50: PRr50Bean? = null
//                    var rr50: Rr50Bean? = null
//                    var amo50ms: Amo50msBean? = null
//                    var mxDmn: MxDmnBean? = null
//                    var si: SiBean? = null
//
//                    class MeanRRIntervalBean {
//                        /**
//                         * value : 709.8022369595227
//                         * measurementUnit : ms
//                         */
//                        var value = 0.0
//                        var measurementUnit: String? = null
//
//                    }
//
//                    class RmssdBean {
//                        /**
//                         * value : 12.843227707013515
//                         * measurementUnit : ms
//                         */
//                        var value = 0.0
//                        var measurementUnit: String? = null
//
//                    }
//
//                    class LnRmssdBean {
//                        /**
//                         * value : 2.5528166457222925
//                         * measurementUnit : ms
//                         */
//                        var value = 0.0
//                        var measurementUnit: String? = null
//
//                    }
//
//                    class SdrrBean {
//                        /**
//                         * value : 18.51266954207822
//                         * measurementUnit : ms
//                         */
//                        var value = 0.0
//                        var measurementUnit: String? = null
//
//                    }
//
//                    class PRr50Bean {
//                        /**
//                         * value : 1.8867924528301887
//                         * measurementUnit : %
//                         */
//                        var value = 0.0
//                        var measurementUnit: String? = null
//
//                    }
//
//                    class Rr50Bean {
//                        /**
//                         * value : 3
//                         * measurementUnit : N.A.
//                         */
//                        var value = 0
//                        var measurementUnit: String? = null
//
//                    }
//
//                    class Amo50msBean {
//                        /**
//                         * value : 71.875
//                         * measurementUnit : %
//                         */
//                        var value = 0.0
//                        var measurementUnit: String? = null
//
//                    }
//
//                    class MxDmnBean {
//                        /**
//                         * value : 129.03997349256713
//                         * measurementUnit : ms
//                         */
//                        var value = 0.0
//                        var measurementUnit: String? = null
//
//                    }
//
//                    class SiBean {
//                        /**
//                         * value : 384
//                         * measurementUnit : N.A.
//                         */
//                        var value = 0
//                        var measurementUnit: String? = null
//
//                    }
//                }
//
//                class FrequencyParametersBean {
//                    /**
//                     * totalPower : {"value":238.2999025233554,"measurementUnit":"ms2"}
//                     * vlfPower : {"value":133.68176305217253,"measurementUnit":"ms2"}
//                     * lfPower : {"value":58.6529424263847,"measurementUnit":"ms2"}
//                     * hfPower : {"value":45.96519704479816,"measurementUnit":"ms2"}
//                     * lfToHf : {"value":1.2760293917422116,"measurementUnit":"ms2"}
//                     */
//                    var totalPower: TotalPowerBean? = null
//                    var vlfPower: VlfPowerBean? = null
//                    var lfPower: LfPowerBean? = null
//                    var hfPower: HfPowerBean? = null
//                    var lfToHf: LfToHfBean? = null
//
//                    class TotalPowerBean {
//                        /**
//                         * value : 238.2999025233554
//                         * measurementUnit : ms2
//                         */
//                        var value = 0.0
//                        var measurementUnit: String? = null
//
//                    }
//
//                    class VlfPowerBean {
//                        /**
//                         * value : 133.68176305217253
//                         * measurementUnit : ms2
//                         */
//                        var value = 0.0
//                        var measurementUnit: String? = null
//
//                    }
//
//                    class LfPowerBean {
//                        /**
//                         * value : 58.6529424263847
//                         * measurementUnit : ms2
//                         */
//                        var value = 0.0
//                        var measurementUnit: String? = null
//
//                    }
//
//                    class HfPowerBean {
//                        /**
//                         * value : 45.96519704479816
//                         * measurementUnit : ms2
//                         */
//                        var value = 0.0
//                        var measurementUnit: String? = null
//
//                    }
//
//                    class LfToHfBean {
//                        /**
//                         * value : 1.2760293917422116
//                         * measurementUnit : ms2
//                         */
//                        var value = 0.0
//                        var measurementUnit: String? = null
//
//                    }
//                }
//            }
        }
    }
}