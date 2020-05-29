package com.app.womba.model

import com.app.womba.base.BaseModel

class BookingResponse : BaseModel {
    /**
     * code : 200
     * message : Appointment booked successfully
     * data : {"doctorId":"5e900b8c8c04d642df956d21","patientId":"5e900d018c04d642df956d2f","paymentId":"5ea198238ab8de6c901c3116","cancelledBy":null,"refund_id":null,"reqBody":{"appointmentDate":"2020-04-23T00:00:00.000Z","doctorId":"5e900b8c8c04d642df956d21","endTime":"2020-04-09T20:15:00+05:30","patientId":"5e900d018c04d642df956d2f","reason":"uhb","startTime":"2020-04-09T19:30:00+05:30","stripe_token":{"id":"tok_1Gb56yKaFUdU2GEA8vAOi5eh","object":"card"},"userTimezone":"Asia/Kolkata"},"is_refund":false,"weekDay":"Thursday","appointmentDate":"2020-04-23T00:00:00.000Z","startHour":14,"startMinute":0,"slotTime":"14:00","availbilityId":null,"clinicTimezone":null,"startSlotZone":"2020-04-09T14:00:00Z","endSlotZone":"2020-04-09T14:45:00Z","appointmentStatus":"3","timeZone":"Asia/Kolkata","doctorReason":"","patientReason":"","Unsuccessfullreason":"","roomId":null,"patientToken":null,"doctorToken":null,"status":"1","isBooked":true,"deleted":false,"finalAppointmentStartwithClincTimezone":"2020-04-23T14:00:00Z","finalAppointmentEndwithClincTimezone":"2020-04-23T14:45:00Z","finalAppointmentwithUserTimezone":"1","documentPath":"","recordingStatus":"Has not been started","consulted":false,"visitType":null,"cheifComplaint":null,"_id":"5ea198238ab8de6c901c3117","color":{"primary":"#493D26","secondary":"#C19A6B"},"createdAt":"2020-04-23T13:29:07.207Z","updatedAt":"2020-04-23T13:29:07.207Z","__v":0}
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
        /**
         * doctorId : 5e900b8c8c04d642df956d21
         * patientId : 5e900d018c04d642df956d2f
         * paymentId : 5ea198238ab8de6c901c3116
         * cancelledBy : null
         * refund_id : null
         * reqBody : {"appointmentDate":"2020-04-23T00:00:00.000Z","doctorId":"5e900b8c8c04d642df956d21","endTime":"2020-04-09T20:15:00+05:30","patientId":"5e900d018c04d642df956d2f","reason":"uhb","startTime":"2020-04-09T19:30:00+05:30","stripe_token":{"id":"tok_1Gb56yKaFUdU2GEA8vAOi5eh","object":"card"},"userTimezone":"Asia/Kolkata"}
         * is_refund : false
         * weekDay : Thursday
         * appointmentDate : 2020-04-23T00:00:00.000Z
         * startHour : 14
         * startMinute : 0
         * slotTime : 14:00
         * availbilityId : null
         * clinicTimezone : null
         * startSlotZone : 2020-04-09T14:00:00Z
         * endSlotZone : 2020-04-09T14:45:00Z
         * appointmentStatus : 3
         * timeZone : Asia/Kolkata
         * doctorReason :
         * patientReason :
         * Unsuccessfullreason :
         * roomId : null
         * patientToken : null
         * doctorToken : null
         * status : 1
         * isBooked : true
         * deleted : false
         * finalAppointmentStartwithClincTimezone : 2020-04-23T14:00:00Z
         * finalAppointmentEndwithClincTimezone : 2020-04-23T14:45:00Z
         * finalAppointmentwithUserTimezone : 1
         * documentPath :
         * recordingStatus : Has not been started
         * consulted : false
         * visitType : null
         * cheifComplaint : null
         * _id : 5ea198238ab8de6c901c3117
         * color : {"primary":"#493D26","secondary":"#C19A6B"}
         * createdAt : 2020-04-23T13:29:07.207Z
         * updatedAt : 2020-04-23T13:29:07.207Z
         * __v : 0
         */
        var doctorId: String? = null
        var patientId: String? = null
        var paymentId: String? = null
        var cancelledBy: Any? = null
        var refund_id: Any? = null
        var reqBody: ReqBodyBean? = null
        var isIs_refund = false
            private set
        var weekDay: String? = null
        var appointmentDate: String? = null
        var startHour = 0
        var startMinute = 0
        var slotTime: String? = null
        var availbilityId: Any? = null
        var clinicTimezone: Any? = null
        var startSlotZone: String? = null
        var endSlotZone: String? = null
        var appointmentStatus: String? = null
        var timeZone: String? = null
        var doctorReason: String? = null
        var patientReason: String? = null
        var unsuccessfullreason: String? = null
        var roomId: Any? = null
        var patientToken: Any? = null
        var doctorToken: Any? = null
        var status: String? = null
        var isIsBooked = false
            private set
        var isDeleted = false
        var finalAppointmentStartwithClincTimezone: String? = null
        var finalAppointmentEndwithClincTimezone: String? = null
        var finalAppointmentwithUserTimezone: String? = null
        var documentPath: String? = null
        var recordingStatus: String? = null
        var isConsulted = false
        var visitType: Any? = null
        var cheifComplaint: Any? = null
        private var _id: String? = null
        var color: ColorBean? = null
        var createdAt: String? = null
        var updatedAt: String? = null
        private var __v = 0

        fun setIs_refund(is_refund: Boolean) {
            isIs_refund = is_refund
        }

        fun setIsBooked(isBooked: Boolean) {
            isIsBooked = isBooked
        }

        fun get_id(): String? {
            return _id
        }

        fun set_id(_id: String?) {
            this._id = _id
        }

        fun get__v(): Int {
            return __v
        }

        fun set__v(__v: Int) {
            this.__v = __v
        }

        class ReqBodyBean {
            /**
             * appointmentDate : 2020-04-23T00:00:00.000Z
             * doctorId : 5e900b8c8c04d642df956d21
             * endTime : 2020-04-09T20:15:00+05:30
             * patientId : 5e900d018c04d642df956d2f
             * reason : uhb
             * startTime : 2020-04-09T19:30:00+05:30
             * stripe_token : {"id":"tok_1Gb56yKaFUdU2GEA8vAOi5eh","object":"card"}
             * userTimezone : Asia/Kolkata
             */
            var appointmentDate: String? = null
            var doctorId: String? = null
            var endTime: String? = null
            var patientId: String? = null
            var reason: String? = null
            var startTime: String? = null
            var stripe_token: StripeTokenBean? = null
            var userTimezone: String? = null

            class StripeTokenBean {
                /**
                 * id : tok_1Gb56yKaFUdU2GEA8vAOi5eh
                 * object : card
                 */
                var id: String? = null
                var `object`: String? = null
            }
        }

        class ColorBean {
            /**
             * primary : #493D26
             * secondary : #C19A6B
             */
            var primary: String? = null
            var secondary: String? = null

        }
    }
}