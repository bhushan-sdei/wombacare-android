package com.app.womba.model.healthProfile

import com.app.womba.base.BaseModel
import com.app.womba.model.postResponseModel.*

class HealthProfileModel :BaseModel{
    /**
     * code : 200
     * message : All Medical Data
     * data : {"_id":"5ea2acacd6d4966ea2f0f987","conditionsData":[{"_id":"5eb512af2441331bca54c9ed","patientId":"5ea2acacd6d4966ea2f0f987","conditionName":"first","currentStatus":"No","medicationTake":"udhdh","isDeleted":false,"createdAt":"2020-05-08T08:05:03.584Z","updatedAt":"2020-05-08T08:05:03.584Z","__v":0},{"_id":"5eb512df2441331bca54c9ee","patientId":"5ea2acacd6d4966ea2f0f987","conditionName":"gshdh","currentStatus":"No","medicationTake":"hdhd","isDeleted":false,"createdAt":"2020-05-08T08:05:51.065Z","updatedAt":"2020-05-08T08:05:51.065Z","__v":0},{"_id":"5eb515752441331bca54c9f0","patientId":"5ea2acacd6d4966ea2f0f987","conditionName":"jh","currentStatus":"No","medicationTake":"bh","isDeleted":false,"createdAt":"2020-05-08T08:16:53.115Z","updatedAt":"2020-05-08T08:16:53.115Z","__v":0}],"medicationData":[],"symptomsData":[],"allergiesData":[{"_id":"5eb5156c2441331bca54c9ef","patientId":"5ea2acacd6d4966ea2f0f987","allergieName":"ggg","currentStatus":"No","allergiesNotes":null,"lastOccurance":"vv","isDeleted":false,"createdAt":"2020-05-08T08:16:44.488Z","updatedAt":"2020-05-08T08:16:44.488Z","__v":0}],"treatmentsData":[{"_id":"5eb5157d2441331bca54c9f1","patientId":"5ea2acacd6d4966ea2f0f987","treatmentName":"hjj","datePerformed":"2002-08-01T00:00:00.000Z","treatmentsNotes":null,"isDeleted":false,"createdAt":"2020-05-08T08:17:01.964Z","updatedAt":"2020-05-08T08:17:01.964Z","__v":0},{"_id":"5eb515882441331bca54c9f2","patientId":"5ea2acacd6d4966ea2f0f987","treatmentName":"jj","datePerformed":"2002-08-01T00:00:00.000Z","treatmentsNotes":null,"isDeleted":false,"createdAt":"2020-05-08T08:17:12.038Z","updatedAt":"2020-05-08T08:17:12.038Z","__v":0},{"_id":"5eb515c22441331bca54c9f3","patientId":"5ea2acacd6d4966ea2f0f987","treatmentName":"jj","datePerformed":"2002-08-01T00:00:00.000Z","treatmentsNotes":null,"isDeleted":false,"createdAt":"2020-05-08T08:18:10.284Z","updatedAt":"2020-05-08T08:18:10.284Z","__v":0}],"immunizationsData":[{"_id":"5eb515ce2441331bca54c9f4","patientId":"5ea2acacd6d4966ea2f0f987","vaccinationName":"bhh","DatePerformed":null,"reactionToVacaination":"kk","isDeleted":false,"createdAt":"2020-05-08T08:18:22.521Z","updatedAt":"2020-05-08T08:18:22.521Z","__v":0}],"covidsData":[]}
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
         * _id : 5ea2acacd6d4966ea2f0f987
         * conditionsData : [{"_id":"5eb512af2441331bca54c9ed","patientId":"5ea2acacd6d4966ea2f0f987","conditionName":"first","currentStatus":"No","medicationTake":"udhdh","isDeleted":false,"createdAt":"2020-05-08T08:05:03.584Z","updatedAt":"2020-05-08T08:05:03.584Z","__v":0},{"_id":"5eb512df2441331bca54c9ee","patientId":"5ea2acacd6d4966ea2f0f987","conditionName":"gshdh","currentStatus":"No","medicationTake":"hdhd","isDeleted":false,"createdAt":"2020-05-08T08:05:51.065Z","updatedAt":"2020-05-08T08:05:51.065Z","__v":0},{"_id":"5eb515752441331bca54c9f0","patientId":"5ea2acacd6d4966ea2f0f987","conditionName":"jh","currentStatus":"No","medicationTake":"bh","isDeleted":false,"createdAt":"2020-05-08T08:16:53.115Z","updatedAt":"2020-05-08T08:16:53.115Z","__v":0}]
         * medicationData : []
         * symptomsData : []
         * allergiesData : [{"_id":"5eb5156c2441331bca54c9ef","patientId":"5ea2acacd6d4966ea2f0f987","allergieName":"ggg","currentStatus":"No","allergiesNotes":null,"lastOccurance":"vv","isDeleted":false,"createdAt":"2020-05-08T08:16:44.488Z","updatedAt":"2020-05-08T08:16:44.488Z","__v":0}]
         * treatmentsData : [{"_id":"5eb5157d2441331bca54c9f1","patientId":"5ea2acacd6d4966ea2f0f987","treatmentName":"hjj","datePerformed":"2002-08-01T00:00:00.000Z","treatmentsNotes":null,"isDeleted":false,"createdAt":"2020-05-08T08:17:01.964Z","updatedAt":"2020-05-08T08:17:01.964Z","__v":0},{"_id":"5eb515882441331bca54c9f2","patientId":"5ea2acacd6d4966ea2f0f987","treatmentName":"jj","datePerformed":"2002-08-01T00:00:00.000Z","treatmentsNotes":null,"isDeleted":false,"createdAt":"2020-05-08T08:17:12.038Z","updatedAt":"2020-05-08T08:17:12.038Z","__v":0},{"_id":"5eb515c22441331bca54c9f3","patientId":"5ea2acacd6d4966ea2f0f987","treatmentName":"jj","datePerformed":"2002-08-01T00:00:00.000Z","treatmentsNotes":null,"isDeleted":false,"createdAt":"2020-05-08T08:18:10.284Z","updatedAt":"2020-05-08T08:18:10.284Z","__v":0}]
         * immunizationsData : [{"_id":"5eb515ce2441331bca54c9f4","patientId":"5ea2acacd6d4966ea2f0f987","vaccinationName":"bhh","DatePerformed":null,"reactionToVacaination":"kk","isDeleted":false,"createdAt":"2020-05-08T08:18:22.521Z","updatedAt":"2020-05-08T08:18:22.521Z","__v":0}]
         * covidsData : []
         */
        private var _id: String? = null
        var conditionsData: ArrayList<Condition>? = null
        var medicationData: ArrayList<Medications>? = null
        var symptomsData: ArrayList<*>? = null
        var allergiesData: ArrayList<Alergies>? = null
        var treatmentsData: ArrayList<Treatment>? = null
        var immunizationsData: ArrayList<Immunization>? = null
        var covidsData: ArrayList<MedicalCovid>? = null

    }
}