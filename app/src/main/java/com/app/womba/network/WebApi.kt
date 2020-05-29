package com.app.womba.network

var WS_BASE_URL = "https://meanstack.stagingsdei.com:5996"

const val  WS_BASE_PATIENT_IMAGE_URL = "https://meanstack.stagingsdei.com:5996/images/patient/"

const val WS_BASE_DOCTOR_IMAGE_URL = "https://meanstack.stagingsdei.com:5996/images/doctor/"

const val STRIPE_PUBLISHABLE_KEY = "pk_test_oVWBUsMgamqWfh5KA6tKCCbE"



const val WS_USER_AUTHENTICATION = "/api/userLogin"
const val WS_USER_FORGOT_PASSWORD = "/api/forgotPassword"
const val WS_PATIENT_REGISTERATION = "/api/patientRegistration"
val WS_MEDIA_UPLOAD_TO_SERVER = "gallery/services/uploadFile"
val WS_MEDIA_DELETE_FROM_SERVER = "gallery/services/deleteFile"
val WS_MEDIA_DOWNLOAD_FROM_SERVER = "gallery/services/allFileList"
val WS_SWIPE_REFRESH_EXAMPLE_URL = "swipe/swipe/swipeRefreshMovieList"

const val WS_GET_DOCTORLIST = "/api/getDoctorList"
const val WS_GET_COUNTRY = "/api/getCountry"
const val WS_GET_Language = "/api/getProviderLanguageList"
const val WS_GET_CITY = "/api/getCity"
const val WS_GET_STATE = "/api/getState"

const val WS_UPDATE_BASIC_INFORMATION = "/api/updatePatientDetails"
const val WS_DOCTOR_SCHDULE_BY_DATE = "/api/getDoctorSchdulebyDate"
val WS_UPDATE_PATIENT_LIFESTYLE = "/api/updatePatientLifeStyle"
val WS_UPDATE_PATIENT_CONTACT = "/api/updatePatientContactInfo"


const val WS_GET_PATIENT_BASIC_INFO = "/api/getPatientBasicInfo"
val WS_GET_PATIENT_LIFESTYLE_INFO = "/api/getPatientLifeStyleInfo"
val WS_GET_PATIENT_CONTACT_INFO = "/api/getPatientContactInfo"

val WS_UPDATE_PROFILE_PIC = "/api/uploadProfilePicApp"
val WS_PROFILE_PIC_URL = "$WS_BASE_URL/assets/images/users/"

val WS_CHANGE_PASSWORD = "/api/userChangePassword"

val WS_VIEW_AVAILABILITY = "/api/viewAvailability"
const val WS_GENERATE_TOKEN = "/api/genrateCallToken"
const val WS_SAVE_EVITALS_DATA = "api/saveEvitalsData"
const val WS_NOTIFICATION_LIST = "api/getpatientNotification"


//----------------Appointment----------------------//

//----------------Appointment----------------------//
const val WS_BOOK_APPOINTMENT = "/api/bookAppointment"

const val WS_PATIENT_PAST_APPOINTMENT = "/api/getPastApointment"
const val WS_PATIENT_BOOKED_APPOINTMENT = "/api/getPatientAppointment"
const val WS_PATIENT_PAYMENT_LIST = "/api/patientPaymentInfo"


//----------------Card Module --------------//

//----------------Card Module --------------//
val WS_ADD_CARD = "/api/addCard"
val WS_GET_CARD = "/api/getCardList"
val WS_SET_DEFAULT_CARD = "/api/setAsDefaultCard"
val WS_DELETE_CARD = "/api/deleteCard"


val WS_GET_USER_DETAIL = "/api/getUserDetail"

const val WS_GET_DOCTOR_EDUCATION_LIST = "/api/getDoctorEducation"
const val WS_GET_ALL_DOCTOR_LIST = "/api/getAllDoctorList"


const val WS_GET_DOCTOR_SPECAILITY_LIST = "/api/getDoctorSpecialityList"

val WS_GET_PATIENT_APPOINTMENT_DETAILS = "/api/getAppointmentDetail"


//add patient data
val WS_PATIENT_CONDITION_SYMPTOM = "/api/addPatientConditionSymptom"
val WS_PATIENT_MEDICATION = "/api/addPatientMedication"
val WS_PATIENT_ALLERGIES = "/api/addPatientAllergy"
val WS_PATIENT_TREATMENT = "/api/addPatientTreatment"
val WS_PATIENT_IMMUNISATION = "/api/addPatientImmunization"
val WS_PATIENT_FAMILY_HISTORY = "/api/addPatientFamilyHistory"

//get patient datalist

//get patient datalist
val WS_PATIENT_CONDITION_SYMPTOM_LIST = "/api/getPatientConditionSymptomList"
val WS_PATIENT_MEDICATION_LIST = "/api/getPatientMedicationList"
val WS_PATIENT_ALLERGIES_LIST = "/api/getPatientAllergyList"
val WS_PATIENT_TREATMENT_LIST = "/api/getPatientTreatmentList"
val WS_PATIENT_IMMUNISATION_LIST = "/api/getPatientImmunizationList"
val WS_PATIENT_FAMILY_HISTORY_LIST = "/api/getPatientFamilyHistoryList"

// update patient datalist
val WS_UPDATE_PATIENT_CONDITION_SYMPTOM = "/api/updatePatientConditionSymptom"
val WS_UPDATE_PATIENT_MEDICATION = "/api/updatePatientMedication"
val WS_UPDATE_PATIENT_ALLERGIES = "/api/updatePatientAllergy"
val WS_UPDATE_PATIENT_TREATMENT = "/api/updatePatientTreatment"
val WS_UPDATE_PATIENT_IMMUNISATION = "/api/updatePatientImmunization"
val WS_UPDATE_PATIENT_FAMILY_HISTORY = "/api/updatePatientFamilyHistory"


// delete patient datalist
val WS_DELETE_PATIENT_CONDITION_SYMPTOM =
    "/api/deletePatientConditionSymptom/{id}"
val WS_DELETE_PATIENT_MEDICATION = "/api/deletePatientMedication/{id}"
val WS_DELETE_PATIENT_ALLERGIES = "/api/deletePatientAllergy/{id}"
val WS_DELETE_PATIENT_TREATMENT = "/api/deletePatientTreatment/{id}"
val WS_DELETE_PATIENT_IMMUNISATION = "/api/deletePatientImmunization/{id}"
val WS_DELETE_PATIENT_FAMILY_HISTORY = "/api/deletePatientFamilyHistory/{id}"


val WS_UPLOAD_PATIENT_OLD_PRESCRIPTION = "/api/uploadPatientOldPrescription"

//----------------------Payment Invoice List -------------------------//

//----------------------Payment Invoice List -------------------------//
val WS_GET_PAYMENT_INVOICE_LIST = "/api/getPatientInvoiceList"


//---------------------Video Call -----------------------------------//

//---------------------Video Call -----------------------------------//
val WS_INTIATE_VIDEO_CALL = "/api/connectVideoCall"

//--------------------- Notification List ----------------------------//

//--------------------- Notification List ----------------------------//
val WS_GET_NOTIFICATION_LIST = "/api/getAllNotificationList"


val WS_READ_NOTIFICATION = "/api/readNotification"

