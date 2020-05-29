package com.app.womba.utils

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Environment
import android.provider.Settings
import android.telephony.TelephonyManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.DrawableCompat
import com.app.womba.AppApplication
import com.app.womba.R
import com.app.womba.model.UserModel
import com.app.womba.ui.splash.SplashActivity
import com.app.womba.utils.InterConsts.USER_DATA
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.iid.FirebaseInstanceId
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

/**
 * Created by shubham on 12/06/19.
 */

val CODE_SUCCESS = 200
val WRONG = 400
val CODE_LOGIN = 201
val CODE_ERROR = 404
val CODE_SESSION_EXPIRED = 500
val TOKEN_INVALID = 401
val INVALID_DATA = 704
val NO_INTERNET_CONNECTION = "12344"
val NO_INTERNET_MESSAGE = "Internet Connection not Available!"

var clientId = "524313088441883"
var redirectUri = "https://charmdapp.com/auth"
var secret = "507292ca8bfa1b48da13b44af082b116"


fun Context.showToast(text: String?, duration: Int = Toast.LENGTH_SHORT) {
    text?.let { Toast.makeText(this, it, duration).show() }
}

fun capitalize(word: String): String {
    return Character.toUpperCase(word[0]) + word.substring(1)
}

inline fun <reified T : Activity> Activity.navigateClass() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

infix fun <T> Boolean.then(param: T): T? = if (this) param else null

// Method to save an bitmap to a file
fun saveTempBitmap(bitmap: Bitmap): Uri? {
    return isExternalStorageWritable() then saveImage(bitmap)
}

private fun saveImage(finalBitmap: Bitmap): Uri? {
    val root = Environment.getExternalStorageDirectory().toString()
    val myDir = File("$root/saved_images")
    myDir.mkdirs()
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    val fname = "eVitals$timeStamp.jpg"
    val file = File(myDir, fname)
    if (file.exists()) file.delete()
    try {
        val out = FileOutputStream(file)
        finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        out.flush()
        out.close()
        return Uri.fromFile(file)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

/**
 * method used for getting Current Date
 */
fun getCurrentDate(): String {
    val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.US)
    val currentDate = sdf.format(Date())
    return currentDate.toString()
}

fun getDateTimeforDefaultTimeZone(dateStr: String): String {
    val df =
        SimpleDateFormat("dd-MM-yyyy HH-mm-ss", Locale.ENGLISH)
    df.timeZone = TimeZone.getTimeZone("UTC")
    val date = df.parse(dateStr)
    df.timeZone = TimeZone.getDefault()
    return df.format(date)
}


/**
 * method used for changing Date Format
 */
fun changeDateFormat(currentFormat: String, requiredFormat: String, dateString: String?): String {
    var result = ""
    if (dateString.isNullOrEmpty()) {
        return result
    }
    val formatterOld = SimpleDateFormat(currentFormat, Locale.getDefault())
    val formatterNew = SimpleDateFormat(requiredFormat, Locale.getDefault())
    var date: Date? = null
    try {
        date = formatterOld.parse(dateString)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    if (date != null) {
        result = formatterNew.format(date)
    }
    return result
}


fun getPreviousDate(days: Int): String {

    val format = DateTimeFormatter.ofPattern("MM/dd/yyyy")
    val date = LocalDate.now()
    val desiredDate = date.minusDays(days.toLong())
//Format and display date
    return desiredDate.format(format)
}


/* Checks if external storage is available for read and write */
fun isExternalStorageWritable(): Boolean {
    val state = Environment.getExternalStorageState()
    if (Environment.MEDIA_MOUNTED.equals(state)) {
        return true
    }
    return false
}

object Screenshot {
    fun takeScreenShot(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(
            view.width,
            view.height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }
}

fun EditText.toStringOrNull(): String? {
    val str = toString()
    return if (str.isEmpty()) null else str
}

fun Activity.responseHandler(statusCode: Int, message: String): Boolean {
    return when (statusCode) {
        CODE_SUCCESS -> true
        CODE_LOGIN -> true
        CODE_ERROR -> {
            false
        }
        WRONG -> {
            showToast(message, Toast.LENGTH_LONG)
            false
        }
        else -> {
            if (statusCode == CODE_SESSION_EXPIRED || statusCode == TOKEN_INVALID) {
                this.showTokenExpiresAlert(this, "Session Expired", "Please Login again")
                showToast(message, Toast.LENGTH_LONG)
            }
            false
        }
    }
}

fun Activity.responseHandlerToast(statusCode: Int, message: String): Boolean {
    showToast(message, Toast.LENGTH_LONG)
    return when (statusCode) {
        CODE_SUCCESS -> true
        CODE_LOGIN -> true
        CODE_ERROR -> {
            false
        }
        WRONG -> {
            showToast(message, Toast.LENGTH_LONG)
            false
        }
        else -> {
            if (statusCode == CODE_SESSION_EXPIRED || statusCode == TOKEN_INVALID) {
                this.showTokenExpiresAlert(this, "Session Expired", "Please Login again")
            }
            false
        }

    }
}

fun Activity.responseHandlerNoToast(statusCode: Int, message: String): Boolean {
    return when (statusCode) {
        CODE_SUCCESS -> true
        CODE_LOGIN -> true
        CODE_ERROR -> {
            false
        }
        WRONG -> {
            showToast(message, Toast.LENGTH_LONG)
            false
        }
        else -> {
            if (statusCode == CODE_SESSION_EXPIRED || statusCode == TOKEN_INVALID) {
                this.showTokenExpiresAlert(this, "Session Expired", "Please Login again")
            }
            false
        }

    }
}

fun handleJson(response: String): Pair<String, String> {
    val obj: JSONObject
    var statusCode: String = "404"
    var message: String = "DIV TAG"
    try {
        obj = JSONObject(response)
        statusCode = obj.getString("statusCode")
        message = obj.getString("message")
    } catch (e: Exception) {

    }
    return Pair(statusCode, message)
}

fun getAppPref(): AppPreference {
    return AppApplication.instance?.let { AppPreference(it) }!!
}


fun getDeviceToken(activity: Activity, returnValue: (String) -> Unit) {
    FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(activity) { result ->
        if (result != null) {
            val token = result.token
            returnValue(token)
        }
    }
}


fun saveUserData(model: UserModel) {
    getAppPref().setString(USER_DATA, Gson().toJson(model))
}

fun getUserData(): UserModel {
    return Gson().fromJson(getAppPref().getString(USER_DATA, ""), UserModel::class.java)
}


fun showSnackBar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

fun showAlertSnackBar(view: View, message: String) {
    val mSnackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
    mSnackbar.view.setBackgroundColor(Color.RED)
    mSnackbar.show()
}

fun showSucessSnackBar(view: View, message: String) {
    val mSnackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
    mSnackbar.view.setBackgroundColor(Color.parseColor("#1ACEEA"))
    mSnackbar.show()
}

fun showInternetAlert(view: View) {
    Snackbar.make(view, R.string.errorInternet, Snackbar.LENGTH_SHORT).show()
}

fun Context.showSnackBarPermission(view: View, activity: Activity) {

    Snackbar.make(
        view, this.getString(R.string.enable_permission),
        Snackbar.LENGTH_LONG
    ).setAction(this.getString(R.string.enable)) {

        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.data = Uri.parse("package:" + activity.packageName)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        activity.startActivity(intent)

    }.setActionTextColor(Color.GRAY).show()
}

fun ImageView.loadUserPhoto(photoUrl: String?) = ifNotDestroyed {
    Glide.with(this).load(photoUrl)
        .transforms(RoundedCorners(context.resources.getDimensionPixelSize(R.dimen._8ssp)))
        .fallback(R.drawable.image_place_holder).into(this)
}

fun ImageView.loadImage(image: String?) = ifNotDestroyed {
    Glide.with(this).load(image).centerCrop().into(this)
}

fun ImageView.loadImageOrHide(image: String?) =
    if (image != null) {
        visibility = View.VISIBLE
        loadImage(image)
    } else {
        visibility = View.GONE
    }

private fun View.ifNotDestroyed(block: () -> Unit) {
    if (!(context as Activity).isDestroyed) {
        block()
    }
}

fun createPartFromString(data: String): RequestBody {
    return RequestBody.create("text/plain".toMediaTypeOrNull(), data)
}


fun Context.setDrawableColor(resource: Int, color: Int): Drawable {
    val responderBackground = ContextCompat.getDrawable(this, resource)
    val wrappedResponder = DrawableCompat.wrap(responderBackground!!)
    DrawableCompat.setTint(wrappedResponder, color)
    return responderBackground
}

fun Context.setDrawableStrokeColor(resource: View, color: Int) {
    val drawable = resource.background as GradientDrawable
    drawable.setStroke(2, color)
}

fun Context.getFileUri(bitmap: Bitmap, mContext: Context): Uri? {
    try {
        val cachePath = File(mContext.cacheDir, "images")
        cachePath.mkdirs() // don't forget to make the directory
        val stream = FileOutputStream("$cachePath/image.png") // overwrites this image every time
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        stream.close()

    } catch (e: IOException) {
        e.printStackTrace()
    }

    val imagePath = File(mContext.cacheDir, "images")
    val newFile = File(imagePath, "image.png")
    val contentUri =
        FileProvider.getUriForFile(mContext, "com.sdei.charmr" + ".fileprovider", newFile)
    return contentUri
}

fun isValidMobile(phone: String): Boolean {
    return android.util.Patterns.PHONE.matcher(phone).matches()
}

fun getCountrycode(): String {
    val telephonyManager: TelephonyManager =
        AppApplication.instance!!.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    val countryISO = telephonyManager.networkCountryIso
//    val countryMnc = telephonyManager.networkOperator

    return countryISO
}


// get date from calender
fun Context.getBirthDate(returnValue: (String) -> Unit) {
    val calender = Calendar.getInstance()
    calender.add(Calendar.YEAR, -18)
    val y = calender.get(Calendar.YEAR)
    val m = calender.get(Calendar.MONTH)
    val d = calender.get(Calendar.DAY_OF_MONTH)
    val dpd = DatePickerDialog(
        this,
        DatePickerDialog.OnDateSetListener { _, year: Int, month: Int, day: Int ->
            // Display Selected date in Toast
            val mon = month + 1
            val mydate = "$mon/$day/$year"
            val simpleDateFormat =
                SimpleDateFormat("mm/dd/yyyy")
            val date = simpleDateFormat.parse(mydate)
            val simpleDateFormat1 =
                SimpleDateFormat("dd-MM-yyyy")
            returnValue(simpleDateFormat1.format(date!!))

        },
        y,
        m,
        d
    )
    dpd.datePicker.maxDate = calender.timeInMillis
    dpd.show()
}


// get date from calender
fun Context.getDate(returnValue: (String) -> Unit) {
    val calender = Calendar.getInstance()
    val y = calender.get(Calendar.YEAR)
    val m = calender.get(Calendar.MONTH)
    val d = calender.get(Calendar.DAY_OF_MONTH)
    val dpd = DatePickerDialog(
        this,
        DatePickerDialog.OnDateSetListener { _, year: Int, month: Int, day: Int ->
            // Display Selected date in Toast
            val mon = month + 1
            val mydate = "$mon/$day/$year"
            val simpleDateFormat =
                SimpleDateFormat("mm/dd/yyyy")
            val date = simpleDateFormat.parse(mydate)
            val simpleDateFormat1 =
                SimpleDateFormat("dd-MM-yyyy")
            returnValue(simpleDateFormat1.format(date!!))

        },
        y,
        m,
        d
    )
    dpd.datePicker.maxDate = calender.timeInMillis
    dpd.show()
}


fun textChecker(string: String): String {
    return if (string.isEmpty()) {
        "--"
    } else {
        string
    }
}

fun getEmojiByUnicode(unicode: Int): String {
    return String(Character.toChars(unicode))
}


fun getFormatDate(timeStamp: String): String {
    //2019-09-06T06:13:02.224Z
    //yyyy-MM-dd'T'HH:mm:ss.SSSZ
    /* val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        input.timeZone = TimeZone.getTimeZone("UTC")
    val output = SimpleDateFormat("MMM dd, yyyy HH:mm aa")
    return try{
        val date = input.parse(timeStamp)
        output.format(date)
    }catch (P: ParseException){
        ""
    }*/
    try {
        val utcFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault())


        val dateGmt = SimpleDateFormat("Z", Locale.US)
        val gmtText = dateGmt.format(calendar.time)

        val utcDate = utcFormat.parse(timeStamp)
        val utcCreate = Calendar.getInstance()
        utcCreate.time = utcDate

        val hh: Int

        val mm: Int
        if (gmtText.trim { it <= ' ' }.length == 3) {

        } else {
            hh = Integer.parseInt(gmtText.substring(1, 3))
            mm = Integer.parseInt(gmtText.substring(3, 5))

            if (gmtText.substring(0, 1) == "+") {
                utcCreate.add(Calendar.HOUR_OF_DAY, hh)
                utcCreate.add(Calendar.MINUTE, mm)
            } else if (gmtText.substring(0, 1) == "-") {
                utcCreate.add(Calendar.HOUR_OF_DAY, -hh)
                utcCreate.add(Calendar.MINUTE, -mm)
            }
        }
        val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
//    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.US)
        return dateFormat.format(utcCreate.time)
    } catch (e: java.lang.Exception) {
        return timeStamp
    }


}

fun getFormatDateTime(timeStamp: String): String {
    //2019-09-06T06:13:02.224Z
    //yyyy-MM-dd'T'HH:mm:ss.SSSZ
    /* val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        input.timeZone = TimeZone.getTimeZone("UTC")
    val output = SimpleDateFormat("MMM dd, yyyy HH:mm aa")
    return try{
        val date = input.parse(timeStamp)
        output.format(date)
    }catch (P: ParseException){
        ""
    }*/
    try {
        val utcFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault())


        val dateGmt = SimpleDateFormat("Z", Locale.US)
        val gmtText = dateGmt.format(calendar.time)

        val utcDate = utcFormat.parse(timeStamp)
        val utcCreate = Calendar.getInstance()
        utcCreate.time = utcDate

        val hh: Int

        val mm: Int
        if (gmtText.trim { it <= ' ' }.length == 3) {

        } else {
            hh = Integer.parseInt(gmtText.substring(1, 3))
            mm = Integer.parseInt(gmtText.substring(3, 5))

            if (gmtText.substring(0, 1) == "+") {
                utcCreate.add(Calendar.HOUR_OF_DAY, hh)
                utcCreate.add(Calendar.MINUTE, mm)
            } else if (gmtText.substring(0, 1) == "-") {
                utcCreate.add(Calendar.HOUR_OF_DAY, -hh)
                utcCreate.add(Calendar.MINUTE, -mm)
            }
        }
        val dateFormat = SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US)
//    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.US)
        return dateFormat.format(utcCreate.time)
    } catch (e: java.lang.Exception) {
        return timeStamp
    }


}


fun getFormatTime(timeStamp: String): String {
    //2019-09-06T06:13:02.224Z
    //yyyy-MM-dd'T'HH:mm:ss.SSSZ
    /* val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        input.timeZone = TimeZone.getTimeZone("UTC")
    val output = SimpleDateFormat("MMM dd, yyyy HH:mm aa")
    return try{
        val date = input.parse(timeStamp)
        output.format(date)
    }catch (P: ParseException){
        ""
    }*/
    try {

        val utcFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault())
        val dateGmt = SimpleDateFormat("Z", Locale.US)
        val gmtText = dateGmt.format(calendar.time)

        val utcDate = utcFormat.parse(timeStamp)
        val utcCreate = Calendar.getInstance()
        utcCreate.time = utcDate

        val hh: Int

        val mm: Int
        if (gmtText.trim { it <= ' ' }.length == 3) {

        } else {
            hh = Integer.parseInt(gmtText.substring(1, 3))
            mm = Integer.parseInt(gmtText.substring(3, 5))

            if (gmtText.substring(0, 1) == "+") {
                utcCreate.add(Calendar.HOUR_OF_DAY, hh)
                utcCreate.add(Calendar.MINUTE, mm)
            } else if (gmtText.substring(0, 1) == "-") {
                utcCreate.add(Calendar.HOUR_OF_DAY, -hh)
                utcCreate.add(Calendar.MINUTE, -mm)
            }
        }
        val dateFormat = SimpleDateFormat("hh:mm a", Locale.US)
//    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.US)
        return dateFormat.format(utcCreate.time)
    } catch (e: java.lang.Exception) {
        return timeStamp
    }


}


fun convertLocaleDateToUTC(
    dob: String,
    format: String?,
    context: Context?
): String? {
    var dateFormat: Date? = null
    var dateAsString = ""
    try {
        println("convertLocaleDateToUTC >>>$dob")
        dateFormat = SimpleDateFormat(format).parse(dob)
        val calendar = Calendar.getInstance()
        calendar.timeZone = TimeZone.getTimeZone("UTC")
        calendar.time = dateFormat
        val time = calendar.time
        val outputFmt =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        dateAsString = outputFmt.format(time)
        println("UTC Date Format >>>>$dateAsString")
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    //  SimpleDateFormat ISO8601DATETIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",context.getResources().getConfiguration().locale);
    return dateAsString
}

fun getTimeZone(): String? {
    val calendar = Calendar.getInstance(TimeZone.getDefault())
    val zone = calendar.timeZone
    return zone.id
}


fun convertLocaleDateToUTC(
    dob: String,
    context: Context?
): String? {
    var dateFormat: Date? = null
    var dateAsString = ""
    try {
        println("convertLocaleDateToUTC >>>$dob")
        dateFormat = SimpleDateFormat("dd-MM-yyyy").parse(dob)
        val calendar = Calendar.getInstance()
        calendar.timeZone = TimeZone.getTimeZone("UTC")
        calendar.time = dateFormat
        val time = calendar.time
        val outputFmt =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        dateAsString = outputFmt.format(time)
        println("UTC Date Format >>>>$dateAsString")
    } catch (e: ParseException) {
        e.printStackTrace()

    }
    return dateAsString
}

// date of birth
fun convertLocaleDateToUTCDob(
    dob: String,
    context: Context?
): String? {
    var dateFormat: Date? = null
    var dateAsString = ""
    try {
        println("convertLocaleDateToUTC >>>$dob")
        dateFormat = SimpleDateFormat("dd-MM-yyyy").parse(dob)
        val calendar = Calendar.getInstance()
        calendar.timeZone = TimeZone.getTimeZone("UTC")
        calendar.time = dateFormat
        val time = calendar.time
        val outputFmt =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ")
        dateAsString = outputFmt.format(time)
        println("UTC Date Format >>>>$dateAsString")
    } catch (e: ParseException) {
        e.printStackTrace()
        return dateAsString
    }
    return dateAsString
}

fun convertDobToLocal(
    dob: String
): String? {
    var dateFormat: Date? = null
    var dateAsString = ""
    try {
        println("convertLocaleDateToUTC >>>$dob")
        dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ").parse(dob)
        val calendar = Calendar.getInstance()
        calendar.timeZone = TimeZone.getTimeZone("UTC")
        calendar.time = dateFormat
        val time = calendar.time
        val outputFmt =
            SimpleDateFormat("dd-MM-yyyy")
        dateAsString = outputFmt.format(time)
        println("UTC Date Format >>>>$dateAsString")
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return dateAsString
}
///

fun getFormatTimeAgo(timeStamp: String): String {
    try {
        val time = getFormatDateTime(timeStamp)

        val utcFormat = SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
        val past = utcFormat.parse(time)
        val now = Date()

        val seconds = TimeUnit.MILLISECONDS.toSeconds(now.time - past!!.time)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(now.time - past.time)
        val hours = TimeUnit.MILLISECONDS.toHours(now.time - past.time)
        val days = TimeUnit.MILLISECONDS.toDays(now.time - past.time)

        return when {
            seconds < 5 -> "some seconds ago"
            seconds in 6..59 -> "$seconds seconds ago"
            minutes < 60 -> "$minutes minutes ago"
            hours < 24 -> "$hours hours ago"
            else -> "$days days ago"
        }
    } catch (j: Exception) {
        j.printStackTrace()
        return getFormatDate(timeStamp)
    }
}

fun getAge(year: Int, month: Int, day: Int): String {
    val dob = Calendar.getInstance()
    val today = Calendar.getInstance()
    dob.set(year, month, day)
    var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
        age--
    }
    val ageInt = age
    return ageInt.toString()
}

fun getAge(dobString: String): Int {
    var date: Date? = null
    val sdf = SimpleDateFormat("MM/dd/yyyy")
    try {
        date = sdf.parse(dobString)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    if (date == null) return 0
    val dob = Calendar.getInstance()
    val today = Calendar.getInstance()
    dob.time = date

    val year = dob.get(Calendar.YEAR)
    val month = dob.get(Calendar.MONTH)
    val day = dob.get(Calendar.DAY_OF_MONTH)

    dob.set(year, month + 1, day)

    var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)

    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
        age--
    }
    return age
}

//fun Context.showFullImage(url: String) {
//    FullImageDialog(this, R.style.pullBottomfromTop, R.layout.dialog_full_image, url).showDialog()
//}
//
//
//fun Context.showFullImage(url: ArrayList<String>) {
//    FullListImageDialog(this, R.style.pullBottomfromTop, R.layout.dialog_full_image, url).showDialog()
//}

fun Activity.hideKeyboard() {
    val view = this.currentFocus
    if (view != null) {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}


fun convertUTCToLocalDate(dob: String?): String? {
    var ourDate = dob
    try {
        val formatter =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val value = formatter.parse(ourDate)
        val dateFormatter =
            SimpleDateFormat("dd-MM-yyyy") //this format changeable
        dateFormatter.timeZone = TimeZone.getDefault()
        ourDate = dateFormatter.format(value)

        //Log.d("ourDate", ourDate);
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        ourDate = dob
    }
    return ourDate
}

fun convertUTCToLocalDateTime(dob: String?): String? {
    var ourDate = dob
    try {
        val formatter =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val value = formatter.parse(ourDate)
        val dateFormatter =
            SimpleDateFormat("dd-MM-yyyy HH:mm aa") //this format changeable
        dateFormatter.timeZone = TimeZone.getDefault()
        ourDate = dateFormatter.format(value)

        //Log.d("ourDate", ourDate);
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        ourDate = dob
    }
    return ourDate
}


fun getFromattedCurrentDate(): String? {
    val currentDate = SimpleDateFormat("dd-MM-yyyy")
    val todayDate = Date()
    val thisDate = currentDate.format(todayDate)
    println("thisDate = $thisDate")
    return thisDate
}


fun getTimeSlots(dob: String?): String? {
    var ourDate = dob
    try {
        val formatter =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        formatter.timeZone = TimeZone.getTimeZone("GMT")
        val value = formatter.parse(ourDate)
        val dateFormatter =
            SimpleDateFormat("hh:mm aa") //this format changeable
        dateFormatter.timeZone = TimeZone.getDefault()
        ourDate = dateFormatter.format(value)

        //Log.d("ourDate", ourDate);
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        ourDate = dob
    }
    return ourDate
}


fun getTime(dob: String?): String? {
    var ourDate = dob
    try {
        val formatter =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
        formatter.timeZone = TimeZone.getTimeZone("GMT")
        val value = formatter.parse(ourDate)
        val dateFormatter =
            SimpleDateFormat("hh:mm aa") //this format changeable
        dateFormatter.timeZone = TimeZone.getDefault()
        ourDate = dateFormatter.format(value)

        //Log.d("ourDate", ourDate);
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        ourDate = dob
    }
    return ourDate
}


fun showOkayAlert(
    context: Context,
    title: String,
    message: String,
    positiveButton: String,
    positiveClickListener: DialogInterface.OnClickListener
) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle(title)
    builder.setMessage(message)
    builder.setPositiveButton(positiveButton, positiveClickListener)
    builder.setCancelable(false)
    builder.show()
}

fun Activity.showLogoutAlert(
    context: Context,
    title: String,
    message: String
) {
    val adb =
        AlertDialog.Builder(context)
    adb.setTitle(title)
    adb.setMessage(message)
    adb.setPositiveButton("OK") { dialog, which ->

        getAppPref().clearShf()
        this.navigateClass<SplashActivity>()
        this.finishAffinity()

    }
    adb.setNegativeButton(
        "Cancel"
    ) { dialog, which ->
    }
    adb.show()
}

fun Activity.showTokenExpiresAlert(
    context: Context,
    title: String,
    message: String
) {
    try {
        val adb =
            AlertDialog.Builder(context)
        adb.setTitle(title)
        adb.setMessage(message)
        adb.setPositiveButton("OK") { dialog, which ->
            getAppPref().clearShf()
            this.navigateClass<SplashActivity>()
            this.finishAffinity()
        }
        adb.show()
    } catch (e: java.lang.Exception) {
        getAppPref().clearShf()
        this.navigateClass<SplashActivity>()
        this.finishAffinity()
    }

}

fun isValidPassword(password: String): Boolean {

    val PASSWORD_PATTERN =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$!%*?&^&+=()?;:',<>/])(?=\\S+$).{4,}$";
//    val PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[d\$@\$!%*?&#])[A-Za-z\\\\dd\$@\$!%*?&#]{4,}";
    val pattern = Pattern.compile(PASSWORD_PATTERN);
    val matcher = pattern.matcher(password);

    return matcher.matches();
}


fun Context.showOKDialog(message: String) {
    showOkayAlert(this,
        resources.getString(R.string.app_name),
        message,
        resources.getString(R.string.ok),
        DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
        })
}

class OptionsModel(
    var _id: String,
    var name: String
)

fun getHeight(): ArrayList<OptionsModel> {
    val arrayList = arrayListOf<OptionsModel>()
    arrayList.add(OptionsModel("0", "4 feet 0 inches"))
    arrayList.add(OptionsModel("0", "4 feet 1 inches"))
    arrayList.add(OptionsModel("0", "4 feet 2 inches"))
    arrayList.add(OptionsModel("0", "4 feet 3 inches"))
    arrayList.add(OptionsModel("0", "4 feet 4 inches"))
    arrayList.add(OptionsModel("0", "4 feet 5 inches"))
    arrayList.add(OptionsModel("0", "4 feet 6 inches"))
    arrayList.add(OptionsModel("0", "4 feet 7 inches"))
    arrayList.add(OptionsModel("0", "4 feet 8 inches"))
    arrayList.add(OptionsModel("0", "4 feet 9 inches"))
    arrayList.add(OptionsModel("0", "4 feet 10 inches"))
    arrayList.add(OptionsModel("0", "4 feet 11 inches"))
    arrayList.add(OptionsModel("0", "5 feet 0 inches"))
    arrayList.add(OptionsModel("0", "5 feet 1 inches"))
    arrayList.add(OptionsModel("0", "5 feet 2 inches"))
    arrayList.add(OptionsModel("0", "5 feet 3 inches"))
    arrayList.add(OptionsModel("0", "5 feet 4 inches"))
    arrayList.add(OptionsModel("0", "5 feet 5 inches"))
    arrayList.add(OptionsModel("0", "5 feet 6 inches"))
    arrayList.add(OptionsModel("0", "5 feet 7 inches"))
    arrayList.add(OptionsModel("0", "5 feet 8 inches"))
    arrayList.add(OptionsModel("0", "5 feet 9 inches"))
    arrayList.add(OptionsModel("0", "5 feet 10 inches"))
    arrayList.add(OptionsModel("0", "5 feet 11 inches"))
    arrayList.add(OptionsModel("0", "6 feet 0 inches"))
    arrayList.add(OptionsModel("0", "6 feet 1 inches"))
    arrayList.add(OptionsModel("0", "6 feet 2 inches"))
    arrayList.add(OptionsModel("0", "6 feet 3 inches"))
    arrayList.add(OptionsModel("0", "6 feet 4 inches"))
    arrayList.add(OptionsModel("0", "6 feet 5 inches"))
    arrayList.add(OptionsModel("0", "6 feet 6 inches"))
    arrayList.add(OptionsModel("0", "6 feet 7 inches"))
    arrayList.add(OptionsModel("0", "6 feet 8 inches"))
    arrayList.add(OptionsModel("0", "6 feet 9 inches"))
    arrayList.add(OptionsModel("0", "6 feet 10 inches"))
    arrayList.add(OptionsModel("0", "6 feet 11 inches"))
    arrayList.add(OptionsModel("0", "7 feet 0 inches"))
    arrayList.add(OptionsModel("0", "7 feet 1 inches"))
    arrayList.add(OptionsModel("0", "7 feet 4 inches"))
    arrayList.add(OptionsModel("0", "7 feet 5 inches"))
    arrayList.add(OptionsModel("0", "7 feet 6 inches"))
    arrayList.add(OptionsModel("0", "7 feet 7 inches"))
    arrayList.add(OptionsModel("0", "7 feet 8 inches"))
    arrayList.add(OptionsModel("0", "7 feet 9 inches"))
    arrayList.add(OptionsModel("0", "7 feet 10 inches"))
    arrayList.add(OptionsModel("0", "7 feet 12 inches"))
    arrayList.add(OptionsModel("0", "8 feet 0 inches"))
    arrayList.add(OptionsModel("0", "8 feet 1 inches"))
    arrayList.add(OptionsModel("0", "8 feet 2 inches"))
    arrayList.add(OptionsModel("0", "8 feet 3 inches"))

    return arrayList
}





