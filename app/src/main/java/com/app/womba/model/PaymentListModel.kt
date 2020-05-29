package com.app.womba.model

import com.app.womba.base.BaseModel
import java.util.*

class PaymentListModel : BaseModel {
    /**
     * code : 200
     * message : Data retrieved successfully
     * data : [{"doctorName":"Ashish Hardiya","ammount":1.3037999999999998,"paymentInvoic":"","AppointmentDate":"2020-Apr-13","AppointmentTime":"14:15 IST","PaymentDate":"2020-04-13T08:03:16.000Z"},{"doctorName":"Ashish Hardiya","ammount":1.3037999999999998,"paymentInvoic":"","AppointmentDate":"2020-Apr-14","AppointmentTime":"16:30 IST","PaymentDate":"2020-04-13T10:02:35.000Z"},{"doctorName":"Ashish Hardiya","ammount":1.3037999999999998,"paymentInvoic":"","AppointmentDate":"2020-Apr-15","AppointmentTime":"18:45 IST","PaymentDate":"2020-04-14T13:18:45.000Z"},{"doctorName":"Ashish Hardiya","ammount":1.3037999999999998,"paymentInvoic":"","AppointmentDate":"2020-Apr-15","AppointmentTime":"11:15 IST","PaymentDate":"2020-04-15T05:03:33.000Z"},{"doctorName":"Ashish Hardiya","ammount":1.3037999999999998,"paymentInvoic":"","AppointmentDate":"2020-Apr-16","AppointmentTime":"9:00 IST","PaymentDate":"2020-04-15T07:23:02.000Z"},{"doctorName":"Ashish Hardiya","ammount":1.3037999999999998,"paymentInvoic":"","AppointmentDate":"2020-Apr-16","AppointmentTime":"15:00 IST","PaymentDate":"2020-04-16T09:30:01.000Z"},{"doctorName":"Ashish Hardiya","ammount":1.3037999999999998,"paymentInvoic":"","AppointmentDate":"2020-Apr-21","AppointmentTime":"12:45 IST","PaymentDate":"2020-04-21T07:18:45.000Z"},{"doctorName":"Ashish Hardiya","ammount":1.3037999999999998,"paymentInvoic":"","AppointmentDate":"2020-Apr-21","AppointmentTime":"9:45 IST","PaymentDate":"2020-04-21T13:51:28.000Z"},{"doctorName":"Ashish Hardiya","ammount":1.3037999999999998,"paymentInvoic":"ch_1Gb52rKaFUdU2GEA0iBsPEQ2","AppointmentDate":"2020-Apr-23","AppointmentTime":"9:45 IST","PaymentDate":"2020-04-23T13:24:49.000Z"},{"doctorName":"Ashish Hardiya","ammount":1.3037999999999998,"paymentInvoic":"ch_1Gb52tKaFUdU2GEAQOH2qBb8","AppointmentDate":"2020-Apr-23","AppointmentTime":"9:45 IST","PaymentDate":"2020-04-23T13:24:51.000Z"},{"doctorName":"Ashish Hardiya","ammount":1.3037999999999998,"paymentInvoic":"ch_1Gb53gKaFUdU2GEAwRY3dtiN","AppointmentDate":"2020-Apr-23","AppointmentTime":"18:00 IST","PaymentDate":"2020-04-23T13:25:41.000Z"},{"doctorName":"Ashish Hardiya","ammount":1.3037999999999998,"paymentInvoic":"ch_1Gb53hKaFUdU2GEAaAoz56UE","AppointmentDate":"2020-Apr-23","AppointmentTime":"18:00 IST","PaymentDate":"2020-04-23T13:25:42.000Z"},{"doctorName":"Ashish Hardiya","ammount":1.3037999999999998,"paymentInvoic":"ch_1Gb53jKaFUdU2GEAsWF2y94I","AppointmentDate":"2020-Apr-23","AppointmentTime":"18:00 IST","PaymentDate":"2020-04-23T13:25:43.000Z"},{"doctorName":"Ashish Hardiya","ammount":1.3037999999999998,"paymentInvoic":"ch_1Gb53kKaFUdU2GEAQZ436mJj","AppointmentDate":"2020-Apr-23","AppointmentTime":"18:00 IST","PaymentDate":"2020-04-23T13:25:44.000Z"},{"doctorName":"Ashish Hardiya","ammount":1.3037999999999998,"paymentInvoic":"ch_1Gb53lKaFUdU2GEAkNAdLJ8k","AppointmentDate":"2020-Apr-23","AppointmentTime":"18:00 IST","PaymentDate":"2020-04-23T13:25:45.000Z"},{"doctorName":"Ashish Hardiya","ammount":1.3037999999999998,"paymentInvoic":"ch_1Gb570KaFUdU2GEAfGFHBKkR","AppointmentDate":"2020-Apr-23","AppointmentTime":"19:30 IST","PaymentDate":"2020-04-23T13:29:07.000Z"},{"doctorName":"Ashish Hardiya","ammount":1.3037999999999998,"paymentInvoic":"ch_1Gb57CKaFUdU2GEAYcbjUoPG","AppointmentDate":"2020-Apr-23","AppointmentTime":"19:30 IST","PaymentDate":"2020-04-23T13:29:19.000Z"},{"doctorName":"Ashish Hardiya","ammount":1.3037999999999998,"paymentInvoic":"ch_1Gb5BNKaFUdU2GEAh4ix5nCt","AppointmentDate":"2020-Apr-23","AppointmentTime":"16:30 IST","PaymentDate":"2020-04-23T13:33:38.000Z"},{"doctorName":"Ashish Hardiya","ammount":1.3037999999999998,"paymentInvoic":"ch_1Gb5KsKaFUdU2GEAkYa74iho","AppointmentDate":"2020-Apr-23","AppointmentTime":"18:45 IST","PaymentDate":"2020-04-23T13:43:26.000Z"},{"doctorName":"Ashish Hardiya","ammount":1.3037999999999998,"paymentInvoic":"","AppointmentDate":"2020-Apr-25","AppointmentTime":"18:45 IST","PaymentDate":"2020-04-25T12: 2020-04-25 20:38:02.336 3372-3465/com.app.womba D/OkHttp: 53:51.000Z"},{"doctorName":"john kawd","ammount":1.3037999999999998,"paymentInvoic":"","AppointmentDate":"2020-Apr-25","AppointmentTime":"19:30 IST","PaymentDate":"2020-04-25T13:20:25.000Z"}]
     */

    constructor(toInt: Int, message: String) : super(toInt, message)
    constructor(message: String?) : super(message!!)
    constructor()

    var data: ArrayList<DataBean>? = null

    class DataBean {
        /**
         * doctorName : Ashish Hardiya
         * ammount : 1.3037999999999998
         * paymentInvoic :
         * AppointmentDate : 2020-Apr-13
         * AppointmentTime : 14:15 IST
         * PaymentDate : 2020-04-13T08:03:16.000Z
         */
        var doctorName: String? = null
        var ammount = 0.0
        var paymentInvoic: String? = "--"
        var appointmentDate: String? = "--"
        var appointmentTime: String? = "--"
        var paymentDate: String? = "--"

    }
}