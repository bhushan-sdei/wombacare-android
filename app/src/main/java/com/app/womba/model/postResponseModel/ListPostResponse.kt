package com.app.womba.model.postResponseModel

data class ListPostResponse(
    val count: String,
    val page: String,
    val searchText: String
)

data class DoctorEducationPost(
    val userId: String
)
data class DoctorSpecialityPost(
    val doctorId: String
)

//"docid" : "5e90311818ff0366d431d659",
//"calendarDate" : "2020-04-06T08:31:00.474Z",
//"userTimeZone" : "Asia\/Kolkata"
data class DoctorSlotTimePost(
    val docid: String,
    val calendarDate: String,
    val userTimeZone: String
)

