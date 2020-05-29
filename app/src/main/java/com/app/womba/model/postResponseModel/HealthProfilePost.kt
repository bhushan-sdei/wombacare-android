package com.app.womba.model.postResponseModel

data class ConditionPost(
    val patientId: String,
    val conditionName: String,
    val currentStatus: String,
    val medicationTake: String
)
data class Condition(
    val patientId: String,
    val _id: String,
    val conditionName: String,
    val currentStatus: String,
    val medicationTake: String
)

data class MedicationsPost(
    val patientId: String,
    val medicineName: String,
    val prescribedBy: String,
    val currentStatus: String,
    val dosage: Int,
    val Unit: String,
    val fromMed: String,
    val toMed: String,
    val pillsLeft: Int,
    val instructions: String,
    val medDate: String,
    val refillReminder: String,
    val medicineNote: String
)

data class Medications(
    val _id: String,
    val patientId: String,
    val medicineName: String,
    val prescribedBy: String,
    val currentStatus: String,
    val dosage: Int,
    val Unit: String,
    val fromMed: String,
    val toMed: String,
    val pillsLeft: Int,
    val instructions: String,
    val medDate: String,
    val refillReminder: String,
    val medicineNote: String
)

data class AlergiesPost(
    val patientId: String,
    val allergieName: String,
    val currentStatus: String,
    val lastOccurance: String
)

data class Alergies(
    val _id: String,
    val patientId: String,
    val allergieName: String,
    val currentStatus: String,
    val lastOccurance: String
)

data class TreatmentPost(
    val patientId: String,
    val treatmentName: String,
    val datePerformed: String

)

data class Treatment(
    val _id: String,
    val patientId: String,
    val treatmentName: String,
    val datePerformed: String

)

data class ImmunizationPost(
    val patientId: String,
    val vaccinationName: String,
    val reactionToVacaination: String
)

data class Immunization(
    val _id: String,
    val patientId: String,
    val vaccinationName: String,
    val reactionToVacaination: String
)

data class MedicalCovidPost(
    val patientId: String,
    val AreyouInfected: String,
    val HospitalName: String,
    val covidTreatment: String
)

data class MedicalCovid(
    val _id: String,
    val patientId: String,
    val AreyouInfected: String,
    val HospitalName: String,
    val covidTreatment: String
)

data class DeleteMedical(
    val patientId: String,
    val medicaId: String,
    val whichMedical: String
)
