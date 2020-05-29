package com.app.womba.model

import com.app.womba.base.BaseModel

class VideoTokenModel  : BaseModel {
    /**
     * code : 200
     * message : User have created token earliar
     * data : {"userId":"5e900d018c04d642df956d2f","appointmentId":"5e9e9e55af39333df6a02b2c","token":"cHJvdmlzaW9uADVlOTAwZDAxOGMwNGQ2NDJkZjk1NmQyZkAxYjQ5MzQudmlkeW8uaW8ANjM3NTUxMTA5MjQAADNmNDE0ZmJjNDI2MjEzNjg2M2M0OGNkZmE5NmM1NzM5OWM3ZGU2MzliZGVmNWE1NDljYjVmMTE0ZWExYTE3ZTgxNGNlYjdiNzY4ZGIzNGQ1YjE4NTVhM2MwYzY1M2M5MQ==","deleted":false,"_id":"5ea3fc8cfee43b23ae36d76c","createdAt":"2020-04-25T09:02:04.713Z","updatedAt":"2020-04-25T09:02:04.713Z","__v":0}
     */

    constructor(toInt: Int, message: String) : super(toInt, message)
    constructor(message: String?) : super(message!!)
    constructor()

    var data: DataBean? = null

    class DataBean {
        /**
         * userId : 5e900d018c04d642df956d2f
         * appointmentId : 5e9e9e55af39333df6a02b2c
         * token : cHJvdmlzaW9uADVlOTAwZDAxOGMwNGQ2NDJkZjk1NmQyZkAxYjQ5MzQudmlkeW8uaW8ANjM3NTUxMTA5MjQAADNmNDE0ZmJjNDI2MjEzNjg2M2M0OGNkZmE5NmM1NzM5OWM3ZGU2MzliZGVmNWE1NDljYjVmMTE0ZWExYTE3ZTgxNGNlYjdiNzY4ZGIzNGQ1YjE4NTVhM2MwYzY1M2M5MQ==
         * deleted : false
         * _id : 5ea3fc8cfee43b23ae36d76c
         * createdAt : 2020-04-25T09:02:04.713Z
         * updatedAt : 2020-04-25T09:02:04.713Z
         * __v : 0
         */
        var userId: String? = null
        var appointmentId: String? = null
        var token: String? = null
        var isDeleted = false
        private var _id: String? = null
        var createdAt: String? = null
        var updatedAt: String? = null
        private var __v = 0

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
    }
}