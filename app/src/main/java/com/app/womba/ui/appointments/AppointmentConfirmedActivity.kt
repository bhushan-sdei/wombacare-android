package com.app.womba.ui.appointments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.womba.R
import com.app.womba.ui.landing.LandingActivity
import com.app.womba.utils.convertUTCToLocalDateTime
import com.app.womba.utils.navigateClass
import kotlinx.android.synthetic.main.activity_appointment_confirmed.*

class AppointmentConfirmedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment_confirmed)

        btnGotIt.setOnClickListener {
            navigateClass<LandingActivity>()
            finishAffinity()
        }
//        yyyy-MM-dd'T'HH:mm:ssZ
        txtDate.text = convertUTCToLocalDateTime(intent.getStringExtra("checkslot"))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navigateClass<LandingActivity>()
    }

}
