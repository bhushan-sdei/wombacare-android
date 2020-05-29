package com.app.womba.model

import com.app.womba.base.BaseModel
import java.util.*

class NotificationListModel  : BaseModel {

    /**
     * code : 200
     * message : Notification received successfully
     * data : [{"email_history_id":"5ea3fd43fee43b23ae36d76d","notification":"tesy","viewed":false,"deleted":false,"_id":"5ea3fd43fee43b23ae36d76e","user_id":"5e900d018c04d642df956d2f","createdAt":"2020-04-25T09:05:07.664Z","updatedAt":"2020-04-25T09:05:07.664Z","__v":0},{"email_history_id":"5ea432e8d5464e3b80b6bf02","notification":"You\u2019ve booked an appointment with  Ashish Hardiya on  Apr-25-2020 18:45 IST","viewed":false,"deleted":false,"_id":"5ea432e8d5464e3b80b6bf03","user_id":"5e900d018c04d642df956d2f","createdAt":"2020-04-25T12:54:00.561Z","updatedAt":"2020-04-25T12:54:00.561Z","__v":0},{"email_history_id":"5ea436a1d5464e3b80b6bf05","notification":"tesy","viewed":false,"deleted":false,"_id":"5ea436a1d5464e3b80b6bf06","user_id":"5e900d018c04d642df956d2f","createdAt":"2020-04-25T13:09:53.936Z","updatedAt":"2020-04-25T13:09:53.936Z","__v":0},{"email_history_id":"5ea436fbd5464e3b80b6bf07","notification":"tesy","viewed":false,"deleted":false,"_id":"5ea436fbd5464e3b80b6bf08","user_id":"5e900d018c04d642df956d2f","createdAt":"2020-04-25T13:11:23.616Z","updatedAt":"2020-04-25T13:11:23.616Z","__v":0},{"email_history_id":"5ea43755d5464e3b80b6bf09","notification":"tesy","viewed":false,"deleted":false,"_id":"5ea43756d5464e3b80b6bf0a","user_id":"5e900d018c04d642df956d2f","createdAt":"2020-04-25T13:12:54.075Z","updatedAt":"2020-04-25T13:12:54.075Z","__v":0},{"email_history_id":"5ea437afd5464e3b80b6bf0b","notification":"tesy","viewed":false,"deleted":false,"_id":"5ea437afd5464e3b80b6bf0c","user_id":"5e900d018c04d642df956d2f","createdAt":"2020-04-25T13:14:23.745Z","updatedAt":"2020-04-25T13:14:23.745Z","__v":0},{"email_history_id":"5ea43809d5464e3b80b6bf0d","notification":"tesy","viewed":false,"deleted":false,"_id":"5ea43809d5464e3b80b6bf0e","user_id":"5e900d018c04d642df956d2f","createdAt":"2020-04-25T13:15:53.671Z","updatedAt":"2020-04-25T13:15:53.671Z","__v":0},{"email_history_id":"5ea43863d5464e3b80b6bf0f","notification":"tesy","viewed":false,"deleted":false,"_id":"5ea43863d5464e3b80b6bf10","user_id":"5e900d018c04d642df956d2f","createdAt":"2020-04-25T13:17:23.585Z","updatedAt":"2020-04-25T13:17:23.585Z","__v":0},{"email_history_id":"5ea4387fd5464e3b80b6bf13","notification":"Appointment is consulted successfully of ashish  paii by Ashish Hardiya Appointment Date : 2020-04-25 Appointment Time :18:45 IST","viewed":false,"deleted":false,"_id":"5ea43880d5464e3b80b6bf14","user_id":"5e900d018c04d642df956d2f","createdAt":"2020-04-25T13:17:52.198Z","updatedAt":"2020-04-25T13:17:52.198Z","__v":0},{"email_history_id":"5ea43921d5464e3b80b6bf1a","notification":"You\u2019ve booked an appointment with  john kawd on  Apr-25-2020 19:30 IST","viewed":false,"deleted":false,"_id":"5ea43921d5464e3b80b6bf1b","user_id":"5e900d018c04d642df956d2f","createdAt":"2020-04-25T13:20:33.799Z","updatedAt":"2020-04-25T13:20:33.799Z","__v":0},{"email_history_id":"5ea83279cc95af6cfe14263c","notification":"You\u2019ve booked an appointment with  john kawd on  Apr-30-2020 9:45 IST","viewed":false,"deleted":false,"_id":"5ea83279cc95af6cfe14263d","user_id":"5e900d018c04d642df956d2f","createdAt":"2020-04-28T13:41:13.693Z","updatedAt":"2020-04-28T13:41:13.693Z","__v":0},{"email_history_id":"5ea834d0cc95af6cfe142643","notification":"You\u2019ve booked an appointment with  Ashish Hardiya on  Apr-29-2020 18:45 IST","viewed":false,"deleted":false,"_id":"5ea834d0cc95af6cfe142644","user_id":"5e900d018c04d642df956d2f","createdAt":"2020-04-28T13:51:12.412Z","updatedAt":"2020-04-28T13:51:12.412Z","__v":0},{"email_history_id":"5ea9231ccf98331e7f6f24b1","notification":"You\u2019ve booked an appointment with  john kawd on  Apr-29-2020 12:45 IST","viewed":false,"deleted":false,"_id":"5ea9231ccf98331e7f6f24b2","user_id":"5e900d018c04d642df956d2f","createdAt":"2020-04-29T06:47:56.925Z","updatedAt":"2020-04-29T06:47:56.925Z","__v":0},{"email_history_id":"5eaa98bd383f3151ace24028","notification":"You\u2019ve booked an appointment with  Ashish Hardiya on  Apr-30-2020 2020-05-05 00:05:36.724 20025-20120/com.app.womba D/OkHttp:  15:00 IST","viewed":false,"deleted":false,"_id":"5eaa98be383f3151ace24029","user_id":"5e900d018c04d642df956d2f","createdAt":"2020-04-30T09:22:06.263Z","updatedAt":"2020-04-30T09:22:06.263Z","__v":0},{"email_history_id":"5eaa9a31383f3151ace2402f","notification":"You\u2019ve booked an appointment with  john kawd on  Apr-30-2020 18:00 IST","viewed":false,"deleted":false,"_id":"5eaa9a32383f3151ace24030","user_id":"5e900d018c04d642df956d2f","createdAt":"2020-04-30T09:28:18.278Z","updatedAt":"2020-04-30T09:28:18.278Z","__v":0},{"email_history_id":"5eaaa64a383f3151ace24036","notification":"You\u2019ve booked an appointment with  john kawd on  Apr-30-2020 21:45 IST","viewed":false,"deleted":false,"_id":"5eaaa64b383f3151ace24037","user_id":"5e900d018c04d642df956d2f","createdAt":"2020-04-30T10:19:55.236Z","updatedAt":"2020-04-30T10:19:55.236Z","__v":0},{"email_history_id":"5eaaaabaf610fe69bd1b6e42","notification":"You\u2019ve booked an appointment with  john kawd on  Apr-30-2020 20:15 IST","viewed":false,"deleted":false,"_id":"5eaaaabaf610fe69bd1b6e43","user_id":"5e900d018c04d642df956d2f","createdAt":"2020-04-30T10:38:50.681Z","updatedAt":"2020-04-30T10:38:50.681Z","__v":0}]
     */
    constructor(toInt: Int, message: String) : super(toInt, message)
    constructor(message: String?) : super(message!!)
    constructor()
    var data: ArrayList<DataBean>? =
        null

    class DataBean {
        /**
         * email_history_id : 5ea3fd43fee43b23ae36d76d
         * notification : tesy
         * viewed : false
         * deleted : false
         * _id : 5ea3fd43fee43b23ae36d76e
         * user_id : 5e900d018c04d642df956d2f
         * createdAt : 2020-04-25T09:05:07.664Z
         * updatedAt : 2020-04-25T09:05:07.664Z
         * __v : 0
         */
        var email_history_id: String? = null
        var notification: String? = null
        var isViewed = false
        var isDeleted = false
        private var _id: String? = null
        var user_id: String? = null
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