package com.app.womba.model

import android.os.Parcel
import android.os.Parcelable
import com.app.womba.base.BaseModel

class AppointmentModel  : BaseModel {

    /**
     * code : 200
     * message : Data retrieved successfully
     * data : [{"_id":"5e9438bb1273171231f8eb3e","doctorId":"5e900b8c8c04d642df956d21","patientId":"5e900d018c04d642df956d2f","weekDay":"Tuesday","appointmentDate":"2020-04-14T10:31:55.017Z","startHour":16,"startMinute":30,"slotTime":"16:30","startSlotZone":"2020-04-07T16:30:00+05:30","endSlotZone":"2020-04-07T17:15:00+05:30","appointmentStatus":"3","status":"1","isBooked":true,"deleted":false,"finalAppointmentStartwithClincTimezone":"2020-04-14T16:30:00+05:30","finalAppointmentEndwithClincTimezone":"2020-04-14T17:15:00+05:30","documentPath":"","createdAt":"2020-04-13T10:02:35.867Z","updatedAt":"2020-04-13T10:02:35.867Z","doctorInfo":{"firstname":"Ashish","lastname":"Hardiya","profile_pic":"noUser.jpg"},"patientInfo":{"patientId":"5e900d018c04d642df956d2f","firstname":"Ashish","lastname":"Hardiya","address":"","profile_pic":"noUser.jpg","dob":"338bd0d0127468306111a45cabba4ddbe31fdee480bd8d34348e7842d3d92e33ce18d8f3928b9f4ed88c6102b60c95ccae0bbcf16622fb3c83","gender":"298fd895"},"Patientdocument":[]},{"_id":"5e95b836c522c81746b65073","doctorId":"5e900b8c8c04d642df956d21","patientId":"5e900d018c04d642df956d2f","weekDay":"Tuesday","appointmentDate":"2020-04-14T18:30:00.000Z","startHour":18,"startMinute":45,"slotTime":"18:45","startSlotZone":"2020-04-07T18:45:00+05:30","endSlotZone":"2020-04-07T19:30:00+05:30","appointmentStatus":"3","status":"1","isBooked":true,"deleted":false,"finalAppointmentStartwithClincTimezone":"2020-04-15T18:45:00+05:30","finalAppointmentEndwithClincTimezone":"2020-04-15T19:30:00+05:30","documentPath":"","createdAt":"2020-04-14T13:18:46.425Z","updatedAt":"2020-04-14T13:18:46.425Z","doctorInfo":{"firstname":"Ashish","lastname":"Hardiya","profile_pic":"noUser.jpg"},"patientInfo":{"patientId":"5e900d018c04d642df956d2f","firstname":"Ashish","lastname":"Hardiya","address":"","profile_pic":"noUser.jpg","dob":"338bd0d0127468306111a45cabba4ddbe31fdee480bd8d34348e7842d3d92e33ce18d8f3928b9f4ed88c6102b60c95ccae0bbcf16622fb3c83","gender":"298fd895"},"Patientdocument":[]}]
     */

    constructor(toInt: Int, message: String) : super(toInt, message)
    constructor(message: String?) : super(message!!)
    constructor()
    var data: ArrayList<DataBean>? = null

    class DataBean():Parcelable {
        /**
         * _id : 5e9438bb1273171231f8eb3e
         * doctorId : 5e900b8c8c04d642df956d21
         * patientId : 5e900d018c04d642df956d2f
         * weekDay : Tuesday
         * appointmentDate : 2020-04-14T10:31:55.017Z
         * startHour : 16
         * startMinute : 30
         * slotTime : 16:30
         * startSlotZone : 2020-04-07T16:30:00+05:30
         * endSlotZone : 2020-04-07T17:15:00+05:30
         * appointmentStatus : 3
         * status : 1
         * isBooked : true
         * deleted : false
         * finalAppointmentStartwithClincTimezone : 2020-04-14T16:30:00+05:30
         * finalAppointmentEndwithClincTimezone : 2020-04-14T17:15:00+05:30
         * documentPath :
         * createdAt : 2020-04-13T10:02:35.867Z
         * updatedAt : 2020-04-13T10:02:35.867Z
         * doctorInfo : {"firstname":"Ashish","lastname":"Hardiya","profile_pic":"noUser.jpg"}
         * patientInfo : {"patientId":"5e900d018c04d642df956d2f","firstname":"Ashish","lastname":"Hardiya","address":"","profile_pic":"noUser.jpg","dob":"338bd0d0127468306111a45cabba4ddbe31fdee480bd8d34348e7842d3d92e33ce18d8f3928b9f4ed88c6102b60c95ccae0bbcf16622fb3c83","gender":"298fd895"}
         * Patientdocument : []
         */
        private var _id: String? = null
        var doctorId: String? = null
        var patientId: String? = null
        var weekDay: String? = null
        var appointmentDate: String? = null
        var startHour = 0
        var startMinute = 0
        var slotTime: String? = null
        var startSlotZone: String? = null
        var endSlotZone: String? = null
        var appointmentStatus: String? = null
        var status: String? = null
        var isIsBooked = false
            private set
        var isDeleted = false
        var finalAppointmentStartwithClincTimezone: String? = null
        var finalAppointmentEndwithClincTimezone: String? = null
        var documentPath: String? = null
        var createdAt: String? = null
        var updatedAt: String? = null
        var doctorInfo: DoctorInfoBean? = null
        var patientInfo: PatientInfoBean? = null
        var patientdocument: List<*>? = null
        var patientconsultedformsData: PatientconsultedformsDataBean? = null

        constructor(parcel: Parcel) : this() {
            _id = parcel.readString()
            doctorId = parcel.readString()
            patientId = parcel.readString()
            weekDay = parcel.readString()
            appointmentDate = parcel.readString()
            startHour = parcel.readInt()
            startMinute = parcel.readInt()
            slotTime = parcel.readString()
            startSlotZone = parcel.readString()
            endSlotZone = parcel.readString()
            appointmentStatus = parcel.readString()
            status = parcel.readString()
            isDeleted = parcel.readByte() != 0.toByte()
            finalAppointmentStartwithClincTimezone = parcel.readString()
            finalAppointmentEndwithClincTimezone = parcel.readString()
            documentPath = parcel.readString()
            createdAt = parcel.readString()
            updatedAt = parcel.readString()
        }

        fun get_id(): String? {
            return _id
        }

        fun set_id(_id: String?) {
            this._id = _id
        }

        fun setIsBooked(isBooked: Boolean) {
            isIsBooked = isBooked
        }

        class DoctorInfoBean():Parcelable {
            /**
             * firstname : Ashish
             * lastname : Hardiya
             * profile_pic : noUser.jpg
             *  "gender": "Male",
            "address": "Bhagwa Nagar",
            "city": "Nagpur",
            "statename": "Maharashtra",
            "country": "India",
            "zip": "440009"
             */
            var firstname: String? = "--"
            var lastname: String? = "--"
            var profile_pic: String? = ""
            var gender: String? = "--"
            var address: String? = "--"
            var city: String? = "--"
            var statename: String? = "--"
            var country: String? = "--"

            constructor(parcel: Parcel) : this() {
                firstname = parcel.readString()
                lastname = parcel.readString()
                profile_pic = parcel.readString()
                gender = parcel.readString()
                address = parcel.readString()
                city = parcel.readString()
                statename = parcel.readString()
                country = parcel.readString()

            }

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(firstname)
                parcel.writeString(lastname)
                parcel.writeString(profile_pic)
                parcel.writeString(gender)
                parcel.writeString(address)
                parcel.writeString(city)
                parcel.writeString(statename)
                parcel.writeString(country)
            }

            override fun describeContents(): Int {
                return 0
            }

            companion object CREATOR : Parcelable.Creator<DoctorInfoBean> {
                override fun createFromParcel(parcel: Parcel): DoctorInfoBean {
                    return DoctorInfoBean(parcel)
                }

                override fun newArray(size: Int): Array<DoctorInfoBean?> {
                    return arrayOfNulls(size)
                }
            }


        }

        class PatientInfoBean() :Parcelable {
            /**
             * patientId : 5e900d018c04d642df956d2f
             * firstname : Ashish
             * lastname : Hardiya
             * address :
             * profile_pic : noUser.jpg
             * dob : 338bd0d0127468306111a45cabba4ddbe31fdee480bd8d34348e7842d3d92e33ce18d8f3928b9f4ed88c6102b60c95ccae0bbcf16622fb3c83
             * gender : 298fd895
             */
            var patientId: String? = null
            var firstname: String? = null
            var lastname: String? = null
            var address: String? = null
            var profile_pic: String? = null
            var dob: String? = null
            var gender: String? = null

            constructor(parcel: Parcel) : this() {
                patientId = parcel.readString()
                firstname = parcel.readString()
                lastname = parcel.readString()
                address = parcel.readString()
                profile_pic = parcel.readString()
                dob = parcel.readString()
                gender = parcel.readString()
            }

            override fun writeToParcel(dest: Parcel, flags: Int) {
                dest.writeString(patientId)
                dest.writeString(firstname)
                dest.writeString(lastname)
                dest.writeString(patientId)
                dest.writeString(address)
                dest.writeString(profile_pic)
                dest.writeString(dob)
                dest.writeString(gender)
            }

            override fun describeContents(): Int {
                return 0
            }

            companion object CREATOR : Parcelable.Creator<PatientInfoBean> {
                override fun createFromParcel(parcel: Parcel): PatientInfoBean {
                    return PatientInfoBean(parcel)
                }

                override fun newArray(size: Int): Array<PatientInfoBean?> {
                    return arrayOfNulls(size)
                }
            }

        }

        class PatientconsultedformsDataBean():Parcelable  {
            /**
             * _id : 5e94352f0adc726657450173
             * appointmentId : 5e941cc4cc5af663dd6d7a47
             * summary_of_health_issues : Test
             * medication_supplements : Test
             * diet_plan : Test
             * yoga_meditation_exercise : Test
             * sleep_advice :
             * other_lifestyle_advice :
             * createdAt : 2020-04-13T09:47:27.333Z
             * updatedAt : 2020-04-13T09:47:27.333Z
             * __v : 0
             */
            private var _id: String? = null
            var appointmentId: String? = null
            var summary_of_health_issues: String? = null
            var medication_supplements: String? = null
            var diet_plan: String? = null
            var yoga_meditation_exercise: String? = null
            var sleep_advice: String? = null
            var other_lifestyle_advice: String? = null
            var createdAt: String? = null
            var updatedAt: String? = null
            private var __v = 0

            constructor(parcel: Parcel) : this() {
                _id = parcel.readString()
                appointmentId = parcel.readString()
                summary_of_health_issues = parcel.readString()
                medication_supplements = parcel.readString()
                diet_plan = parcel.readString()
                yoga_meditation_exercise = parcel.readString()
                sleep_advice = parcel.readString()
                other_lifestyle_advice = parcel.readString()
                createdAt = parcel.readString()
                updatedAt = parcel.readString()
                __v = parcel.readInt()
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

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(_id)
                parcel.writeString(appointmentId)
                parcel.writeString(summary_of_health_issues)
                parcel.writeString(medication_supplements)
                parcel.writeString(diet_plan)
                parcel.writeString(yoga_meditation_exercise)
                parcel.writeString(sleep_advice)
                parcel.writeString(other_lifestyle_advice)
                parcel.writeString(createdAt)
                parcel.writeString(updatedAt)
                parcel.writeInt(__v)
            }

            override fun describeContents(): Int {
                return 0
            }

            companion object CREATOR : Parcelable.Creator<PatientconsultedformsDataBean> {
                override fun createFromParcel(parcel: Parcel): PatientconsultedformsDataBean {
                    return PatientconsultedformsDataBean(parcel)
                }

                override fun newArray(size: Int): Array<PatientconsultedformsDataBean?> {
                    return arrayOfNulls(size)
                }
            }
        }


        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(_id)
            parcel.writeString(doctorId)
            parcel.writeString(patientId)
            parcel.writeString(weekDay)
            parcel.writeString(appointmentDate)
            parcel.writeInt(startHour)
            parcel.writeInt(startMinute)
            parcel.writeString(slotTime)
            parcel.writeString(startSlotZone)
            parcel.writeString(endSlotZone)
            parcel.writeString(appointmentStatus)
            parcel.writeString(status)
            parcel.writeByte(if (isDeleted) 1 else 0)
            parcel.writeString(finalAppointmentStartwithClincTimezone)
            parcel.writeString(finalAppointmentEndwithClincTimezone)
            parcel.writeString(documentPath)
            parcel.writeString(createdAt)
            parcel.writeString(updatedAt)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<DataBean> {
            override fun createFromParcel(parcel: Parcel): DataBean {
                return DataBean(parcel)
            }

            override fun newArray(size: Int): Array<DataBean?> {
                return arrayOfNulls(size)
            }
        }
    }
}