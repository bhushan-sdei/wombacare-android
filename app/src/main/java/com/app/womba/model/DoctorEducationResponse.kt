package com.app.womba.model

import com.app.womba.base.BaseModel

class DoctorEducationResponse :BaseModel{
    /**
     * code : 200
     * message : Data retrieved successfully
     * data : {"educations":[{"college":"test","degree":"test","year":14443,"_id":null}]}
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
        var educations: ArrayList<EducationsBean>? = null

        class EducationsBean {
            /**
             * college : test
             * degree : test
             * year : 14443
             * _id : null
             */
            var college: String? = null
            var degree: String? = null
            var year: String? = null
            private var _id: Any? = null

            fun get_id(): Any? {
                return _id
            }

            fun set_id(_id: Any?) {
                this._id = _id
            }
        }
    }
}