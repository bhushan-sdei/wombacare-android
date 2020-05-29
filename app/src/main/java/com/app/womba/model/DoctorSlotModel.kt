package com.app.womba.model

import com.app.womba.base.BaseModel
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

class DoctorSlotModel: BaseModel {
    /**
     * code : 200
     * message : Data Retrived Successfully
     * data : [{"actions":[{"label":"**"><\/i>"},{"label":"**"><\/i>"}],"allDay":true,"start":"2020-04-11T09:00:00+05:30","color":{"primary":"#0E2347","secondary":"#18202d"},"draggable":true,"resizable":{"beforeStart":true,"afterEnd":true},"title":"Saturday","end":"2020-04-11T09:45:00+05:30","checkslot":"2020-04-18T09:00:00+05:30"},{"actions":[{"label":"**"><\/i>"},{"label":"**"><\/i>"}],"allDay":true,"start":"2020-04-11T09:45:00+05:30","color":{"primary":"#0E2347","secondary":"#18202d"},"draggable":true,"resizable":{"beforeStart":true,"afterEnd":true},"title":"Saturday","end":"2020-04-11T10:30:00+05:30","checkslot":"2020-04-18T09:45:00+05:30"},{"actions":[{"label":"**"><\/i>"},{"label":"**"><\/i>"}],"allDay":true,"start":"2020-04-11T10:30:00+05:30","color":{"primary":"#0E2347","secondary":"#18202d"},"draggable":true,"resizable":{"beforeStart":true,"afterEnd":true},"title":"Saturday","end":"2020-04-11T11:15:00+05:30","checkslot":"2020-04-18T10:30:00+05:30"},{"actions":[{"label":"**"><\/i>"},{"label":"**"><\/i>"}],"allDay":true,"start":"2020-04-11T11:15:00+05:30","color":{"primary":"#0E2347","secondary":"#18202d"},"draggable":true,"resizable":{"beforeStart":true,"afterEnd":true},"title":"Saturday","end":"2020-04-11T12:00:00+05:30","checkslot":"2020-04-18T11:15:00+05:30"},{"actions":[{"label":"**"><\/i>"},{"label":"**"><\/i>"}],"allDay":true,"start":"2020-04-11T12:00:00+05:30","color":{"primary":"#0E2347","secondary":"#18202d"},"draggable":true,"resizable":{"beforeStart":true,"afterEnd":true},"title":"Saturday","end":"2020-04-11T12:45:00+05:30","checkslot":"2020-04-18T12:00:00+05:30"},{"actions":[{"label":"**"><\/i>"},{"label":"**"><\/i>"}],"allDay":true,"start":"2020-04-11T12:45:00+05:30","color":{"primary":"#0E2347","secondary":"#18202d"},"draggable":true,"resizable":{"beforeStart":true,"afterEnd":true},"title":"Saturday","end":"2020-04-11T13:30:00+05:30","checkslot":"2020-04-18T12:45:00+05:30"},{"actions":[{"label":"**"><\/i>"},{"label":"**"><\/i>"}],"allDay":true,"start":"2020-04-11T13:30:00+05:30","color":{"primary":"#0E2347","secondary":"#18202d"},"draggable":true,"resizable":{"beforeStart":true,"afterEnd":true},"title":"Saturday","end":"2020-04-11T14:15:00+05:30","checkslot":"2020-04-18T13:30:00+05:30"},{"actions":[{"label":"**"><\/i>"},{"label":"**"><\/i>"}],"allDay":true,"start":"2020-04-11T14:15:00+05:30","color":{"primary":"#0E2347","secondary":"#18202d"},"draggable":true,"resizable":{"beforeStart":true,"afterEnd":true},"title":"Saturday","end":"2020-04-11T15:00:00+05:30","checkslot":"2020-04-18T14:15:00+05:30"},{"actions":[{"label":"**"><\/i>"},{"label":"**"><\/i>"}],"allDay":true,"start":"2020-04-11T15:00:00+05:30","color":{"primary":"#0E2347","secondary":"#18202d"},"draggable":true,"resizable":{"beforeStart":true,"afterEnd":true},"title":"Saturday","end":"2020-04-11T15:45:00+05:30","checkslot":"2020-04-18T15:00:00+05:30"},{"actions":[{"label":"**"><\/i>"},{"label":"**"><\/i>"}],"allDay":true,"start":"2020-04-11T15:45:00+05:30","color":{"primary":"#0E2347","secondary":"#18202d"},"draggable":true,"resizable":{"beforeStart":true,"afterEnd":true},"title":"Saturday","end":"2020-04-11T16:30:00+05:30","checkslot":"2020-04-18T15:45:00+05:30"},{"actions":[{"label":"**"><\/i>"},{"label":"**"><\/i>"}],"allDay":true,"start":"2020-04-11T16:30:00+05:30","color":{"primary":"#0E2347","secondary":"#18202d"},"draggabl 2020-04-18 14:33:16.452 15276-15349/com.app.womba D/OkHttp: e":true,"resizable":{"beforeStart":true,"afterEnd":true},"title":"Saturday","end":"2020-04-11T17:15:00+05:30","checkslot":"2020-04-18T16:30:00+05:30"},{"actions":[{"label":"**"><\/i>"},{"label":"**"><\/i>"}],"allDay":true,"start":"2020-04-11T17:15:00+05:30","color":{"primary":"#0E2347","secondary":"#18202d"},"draggable":true,"resizable":{"beforeStart":true,"afterEnd":true},"title":"Saturday","end":"2020-04-11T18:00:00+05:30","checkslot":"2020-04-18T17:15:00+05:30"},{"actions":[{"label":"**"><\/i>"},{"label":"**"><\/i>"}],"allDay":true,"start":"2020-04-11T18:00:00+05:30","color":{"primary":"#0E2347","secondary":"#18202d"},"draggable":true,"resizable":{"beforeStart":true,"afterEnd":true},"title":"Saturday","end":"2020-04-11T18:45:00+05:30","checkslot":"2020-04-18T18:00:00+05:30"},{"actions":[{"label":"**"><\/i>"},{"label":"**"><\/i>"}],"allDay":true,"start":"2020-04-11T18:45:00+05:30","color":{"primary":"#0E2347","secondary":"#18202d"},"draggable":true,"resizable":{"beforeStart":true,"afterEnd":true},"title":"Saturday","end":"2020-04-11T19:30:00+05:30","checkslot":"2020-04-18T18:45:00+05:30"},{"actions":[{"label":"**"><\/i>"},{"label":"**"><\/i>"}],"allDay":true,"start":"2020-04-11T19:30:00+05:30","color":{"primary":"#0E2347","secondary":"#18202d"},"draggable":true,"resizable":{"beforeStart":true,"afterEnd":true},"title":"Saturday","end":"2020-04-11T20:15:00+05:30","checkslot":"2020-04-18T19:30:00+05:30"},{"actions":[{"label":"**"><\/i>"},{"label":"**"><\/i>"}],"allDay":true,"start":"2020-04-11T20:15:00+05:30","color":{"primary":"#0E2347","secondary":"#18202d"},"draggable":true,"resizable":{"beforeStart":true,"afterEnd":true},"title":"Saturday","end":"2020-04-11T21:00:00+05:30","checkslot":"2020-04-18T20:15:00+05:30"},{"actions":[{"label":"**"><\/i>"},{"label":"**"><\/i>"}],"allDay":true,"start":"2020-04-11T21:00:00+05:30","color":{"primary":"#0E2347","secondary":"#18202d"},"draggable":true,"resizable":{"beforeStart":true,"afterEnd":true},"title":"Saturday","end":"2020-04-11T21:45:00+05:30","checkslot":"2020-04-18T21:00:00+05:30"},{"actions":[{"label":"**"><\/i>"},{"label":"**"><\/i>"}],"allDay":true,"start":"2020-04-11T21:45:00+05:30","color":{"primary":"#0E2347","secondary":"#18202d"},"draggable":true,"resizable":{"beforeStart":true,"afterEnd":true},"title":"Saturday","end":"2020-04-11T22:30:00+05:30","checkslot":"2020-04-18T21:45:00+05:30"}]
     */


    constructor(toInt: Int, message: String) : super(toInt, message)
    constructor(message: String?) : super(message!!)
    constructor()
    private var data: ArrayList<DataBean>? = null

    fun getData(): ArrayList<DataBean>? {
        return data
    }

    fun setData(data: ArrayList<DataBean>?) {
        this.data = data
    }

    class DataBean {
        /**
         * actions : [{"label":"**"><\/i>"},{"label":"**"><\/i>"}]
         * allDay : true
         * start : 2020-04-11T09:00:00+05:30
         * color : {"primary":"#0E2347","secondary":"#18202d"}
         * draggable : true
         * resizable : {"beforeStart":true,"afterEnd":true}
         * title : Saturday
         * end : 2020-04-11T09:45:00+05:30
         * checkslot : 2020-04-18T09:00:00+05:30
         * draggabl 2020-04-18 14:33:16.452 15276-15349/com.app.womba D/OkHttp: e : true
         */
        var isAllDay = false
        var start: String? = null
        var color: ColorBean? = null
        var selected:Boolean=false
        var isDraggable = false
        var resizable: ResizableBean? = null
        var title: String? = null
        var end: String? = null
        var checkslot: String? = null

        var actions: List<ActionsBean>? = null


        class ColorBean {
            /**
             * primary : #0E2347
             * secondary : #18202d
             */
            var primary: String? = null
            var secondary: String? = null

        }

        class ResizableBean {
            /**
             * beforeStart : true
             * afterEnd : true
             */
            var isBeforeStart = false
            var isAfterEnd = false

        }

        class ActionsBean {
            /**
             * label : **
             */
            var label: String? = null

        }
    }
}