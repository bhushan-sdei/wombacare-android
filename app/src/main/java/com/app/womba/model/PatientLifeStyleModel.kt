package com.app.womba.model

import com.app.womba.base.BaseModel
import java.util.*

class PatientLifeStyleModel : BaseModel {
    /**
     * code : 200
     * message : Life Style data saved succssfully
     * data : [{"patientId":"5ea2acacd6d4966ea2f0f987","dietaryRestrictions":"Lactose-Vegetarian","alcoholConsumption":"Not a Drinker","tobaccoConsumption":"","sexEngageTime":"More than 3-5 week","diabetes":"No","caffeine":"Coffee","smoked":"Heavy","annualPhysical":"No","exercise":"Yes","drugsOrMedication":"Yes","fitnessTracker":"Yes","sleep":"Yes","_id":"5eb67cfc10f71d21ff38acf4","createdAt":"2020-05-09T09:50:52.519Z","updatedAt":"2020-05-09T09:50:52.519Z","__v":0}]
     * responseStatus : 1
     * token :
     */


    constructor(toInt: Int, message: String) : super(toInt, message)
    constructor(message: String?) : super(message!!)
    constructor()

    var responseStatus = 0
    var token: String? = null
    var data: ArrayList<DataBean>? =
        null

    class DataBean {
        /**
         * patientId : 5ea2acacd6d4966ea2f0f987
         * dietaryRestrictions : Lactose-Vegetarian
         * alcoholConsumption : Not a Drinker
         * tobaccoConsumption :
         * sexEngageTime : More than 3-5 week
         * diabetes : No
         * caffeine : Coffee
         * smoked : Heavy
         * annualPhysical : No
         * exercise : Yes
         * drugsOrMedication : Yes
         * fitnessTracker : Yes
         * sleep : Yes
         * _id : 5eb67cfc10f71d21ff38acf4
         * createdAt : 2020-05-09T09:50:52.519Z
         * updatedAt : 2020-05-09T09:50:52.519Z
         * __v : 0
         */
        var patientId: String? = null
        var dietaryRestrictions: String? = null
        var alcoholConsumption: String? = null
        var tobaccoConsumption: String? = null
        var sexEngageTime: String? = null
        var diabetes: String? = null
        var caffeine: String? = null
        var smoked: String? = null
        var annualPhysical: String? = null
        var exercise: String? = null
        var drugsOrMedication: String? = null
        var fitnessTracker: String? = null
        var sleep: String? = null
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