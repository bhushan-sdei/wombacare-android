package com.app.womba.model

import android.os.Parcel
import android.os.Parcelable
import com.app.womba.base.BaseModel

class DoctorListResponse : BaseModel {
    /**
     * code : 200
     * message : Data retrieved successfully
     * data : {"data":[{"_id":"5cee7265e88d462135d3faf8","phone_number":"deleted","profile_pic":"deleted","experience":"122","address":"Street Number","city":"Nagpur","state":null,"zip":"440008","country":"India","gender":"Male","websitelink":"deleted","formlink":"deleted","userTimezone":"Asia/Kolkata","fee":"5002","biography":"deleted","firstname":"208bd89535747c","lastname":"208bd89535747c","email":"5cee7265e88d462135d3faf8@yopmail.com","specialityInfo":[],"doctorRatingData":[]},{"_id":"5cee75bbe88d462135d3fb09","phone_number":"deleted","profile_pic":"deleted","experience":"122","address":"Street Number","city":"Nagpur","state":null,"zip":"440008","country":"India","gender":"Male","websitelink":"deleted","formlink":"deleted","userTimezone":"Asia/Kolkata","fee":"5002","biography":"deleted","firstname":"208bd89535747c","lastname":"208bd89535747c","email":"5cee75bbe88d462135d3fb09@yopmail.com","specialityInfo":[],"doctorRatingData":[]}],"totalCount":34,"consultancy_fees":100}
     */
    constructor(toInt: Int, message: String) : super(toInt, message)

    constructor(message: String?) : super(message!!)

    constructor()
    var data: DataBeanX? = null

    class DataBeanX {
        /**
         * data : [{"_id":"5cee7265e88d462135d3faf8","phone_number":"deleted","profile_pic":"deleted","experience":"122","address":"Street Number","city":"Nagpur","state":null,"zip":"440008","country":"India","gender":"Male","websitelink":"deleted","formlink":"deleted","userTimezone":"Asia/Kolkata","fee":"5002","biography":"deleted","firstname":"208bd89535747c","lastname":"208bd89535747c","email":"5cee7265e88d462135d3faf8@yopmail.com","specialityInfo":[],"doctorRatingData":[]},{"_id":"5cee75bbe88d462135d3fb09","phone_number":"deleted","profile_pic":"deleted","experience":"122","address":"Street Number","city":"Nagpur","state":null,"zip":"440008","country":"India","gender":"Male","websitelink":"deleted","formlink":"deleted","userTimezone":"Asia/Kolkata","fee":"5002","biography":"deleted","firstname":"208bd89535747c","lastname":"208bd89535747c","email":"5cee75bbe88d462135d3fb09@yopmail.com","specialityInfo":[],"doctorRatingData":[]}]
         * totalCount : 34
         * consultancy_fees : 100
         */
        var totalCount = 0
        var consultancy_fees = 0
        var data: ArrayList<DataBean>? =
            null

        class DataBean() : Parcelable {
            /**
             * _id : 5cee7265e88d462135d3faf8
             * phone_number : deleted
             * profile_pic : deleted
             * experience : 122
             * address : Street Number
             * city : Nagpur
             * state : null
             * zip : 440008
             * country : India
             * gender : Male
             * websitelink : deleted
             * formlink : deleted
             * userTimezone : Asia/Kolkata
             * fee : 5002
             * biography : deleted
             * firstname : 208bd89535747c
             * lastname : 208bd89535747c
             * email : 5cee7265e88d462135d3faf8@yopmail.com
             * specialityInfo : []
             * doctorRatingData : []
             */
            private var _id: String? = null
            var phone_number: String? = ""
            var profile_pic: String? = ""
            var experience: String? = ""
            var address: String? = ""
            var city: String? = ""
            var state: String? = ""
            var zip: String? = ""
            var country: String? = ""
            var gender: String? = ""
            var websitelink: String? = ""
            var formlink: String? = ""
            var userTimezone: String? = ""
            var fee: String? = ""
            var biography: String? = ""
            var firstname: String? = ""
            var lastname: String? = ""
            var email: String? = ""
            var specialityInfo: ArrayList<SpecialityInfoBean>? = null
            var doctorRatingData: List<*>? = null

            constructor(parcel: Parcel) : this() {
                _id = parcel.readString()
                phone_number = parcel.readString()
                profile_pic = parcel.readString()
                experience = parcel.readString()
                address = parcel.readString()
                city = parcel.readString()
                zip = parcel.readString()
                country = parcel.readString()
                gender = parcel.readString()
                websitelink = parcel.readString()
                formlink = parcel.readString()
                userTimezone = parcel.readString()
                fee = parcel.readString()
                biography = parcel.readString()
                firstname = parcel.readString()
                lastname = parcel.readString()
                email = parcel.readString()
                specialityInfo = parcel.createTypedArrayList(SpecialityInfoBean)
            }

            class SpecialityInfoBean() :Parcelable {
                /**
                 * name : Abuse
                 * services : [{"service":"test","_id":"5e8c530b29edfa74184b32c4"},{"service":"test 2","_id":"5e8c530b29edfa74184b32c5"},{"service":"test3","_id":null}]
                 */
                var name: String? = null
                var services: List<ServicesBean>? = null

                constructor(parcel: Parcel) : this() {
                    name = parcel.readString()
                }

                class ServicesBean() :Parcelable {
                    /**
                     * service : test
                     * _id : 5e8c530b29edfa74184b32c4
                     */
                    var service: String? = null
                    private var _id: String? = null

                    constructor(parcel: Parcel) : this() {
                        service = parcel.readString()
                        _id = parcel.readString()
                    }

                    fun get_id(): String? {
                        return _id
                    }

                    fun set_id(_id: String?) {
                        this._id = _id
                    }

                    override fun writeToParcel(parcel: Parcel, flags: Int) {
                        parcel.writeString(service)
                        parcel.writeString(_id)
                    }

                    override fun describeContents(): Int {
                        return 0
                    }

                    companion object CREATOR : Parcelable.Creator<ServicesBean> {
                        override fun createFromParcel(parcel: Parcel): ServicesBean {
                            return ServicesBean(parcel)
                        }

                        override fun newArray(size: Int): Array<ServicesBean?> {
                            return arrayOfNulls(size)
                        }
                    }
                }

                override fun writeToParcel(parcel: Parcel, flags: Int) {
                    parcel.writeString(name)
                }

                override fun describeContents(): Int {
                    return 0
                }

                companion object CREATOR : Parcelable.Creator<SpecialityInfoBean> {
                    override fun createFromParcel(parcel: Parcel): SpecialityInfoBean {
                        return SpecialityInfoBean(parcel)
                    }

                    override fun newArray(size: Int): Array<SpecialityInfoBean?> {
                        return arrayOfNulls(size)
                    }
                }
            }


            fun get_id(): String? {
                return _id
            }

            fun set_id(_id: String?) {
                this._id = _id
            }

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(_id)
                parcel.writeString(phone_number)
                parcel.writeString(profile_pic)
                parcel.writeString(experience)
                parcel.writeString(address)
                parcel.writeString(city)
                parcel.writeString(zip)
                parcel.writeString(country)
                parcel.writeString(gender)
                parcel.writeString(websitelink)
                parcel.writeString(formlink)
                parcel.writeString(userTimezone)
                parcel.writeString(fee)
                parcel.writeString(biography)
                parcel.writeString(firstname)
                parcel.writeString(lastname)
                parcel.writeString(email)
                parcel.writeTypedList(specialityInfo)
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
}