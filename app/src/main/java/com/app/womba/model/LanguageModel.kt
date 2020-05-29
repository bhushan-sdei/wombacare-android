package com.app.womba.model

import android.provider.ContactsContract
import com.app.womba.base.BaseModel
import com.app.womba.utils.OptionsModel

class LanguageModel  : BaseModel {
    /**
     * code : 200
     * message : Data retrieved successfully
     * data : {"data":[{"_id":"5d9ee78b1070171562482cae","name":"English"},{"_id":"5da5c35445efa43461398500","name":"Southern Sotho"},{"_id":"5da5c35445efa43461398501","name":"Tsonga"},{"_id":"5da5c35445efa43461398502","name":"Afrikaans"},{"_id":"5da5c35445efa43461398504","name":"Tswana"},{"_id":"5da5c35445efa43461398505","name":"Swati"},{"_id":"5da5c35445efa43461398506","name":"Zulu"},{"_id":"5da5c35445efa43461398507","name":"Venda"},{"_id":"5da5c35445efa43461398508","name":"Ndebele"},{"_id":"5da5c35445efa43461398509","name":"Xhosa"},{"_id":"5da5c35445efa4346139850a","name":"Northen Sotho"}],"totalCount":11}
     */

    constructor(toInt: Int, message: String) : super(toInt, message)
    constructor(message: String?) : super(message!!)
    constructor()
    var data: DataBeanX? = null

    class DataBeanX {
        /**
         * data : [{"_id":"5d9ee78b1070171562482cae","name":"English"},{"_id":"5da5c35445efa43461398500","name":"Southern Sotho"},{"_id":"5da5c35445efa43461398501","name":"Tsonga"},{"_id":"5da5c35445efa43461398502","name":"Afrikaans"},{"_id":"5da5c35445efa43461398504","name":"Tswana"},{"_id":"5da5c35445efa43461398505","name":"Swati"},{"_id":"5da5c35445efa43461398506","name":"Zulu"},{"_id":"5da5c35445efa43461398507","name":"Venda"},{"_id":"5da5c35445efa43461398508","name":"Ndebele"},{"_id":"5da5c35445efa43461398509","name":"Xhosa"},{"_id":"5da5c35445efa4346139850a","name":"Northen Sotho"}]
         * totalCount : 11
         */
        var totalCount = 0
        var data: ArrayList<DataBean>? =
            null

        class DataBean {
            /**
             * _id : 5d9ee78b1070171562482cae
             * name : English
             */
            private var _id: String? = null
            var name: String? = null
            fun get_id(): String? {
                return _id
            }

            fun set_id(_id: String?) {
                this._id = _id
            }

        }
    }
}