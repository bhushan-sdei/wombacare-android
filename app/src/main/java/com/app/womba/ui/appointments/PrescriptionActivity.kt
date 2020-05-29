package com.app.womba.ui.appointments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.womba.R
import com.app.womba.model.AppointmentModel
import com.app.womba.utils.InterConsts
import com.app.womba.utils.textChecker
import kotlinx.android.synthetic.main.activity_prescription.*

class PrescriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prescription)
        setData()
        imgBack.setOnClickListener {
            finish()
        }
    }

    fun setData(){
        val mData = intent.getParcelableExtra<AppointmentModel.DataBean.PatientconsultedformsDataBean>(InterConsts.PRESCRIPTION)
        summary_of_health_issues.setText(textChecker(mData!!.summary_of_health_issues!!))
        medication_supplements.setText(textChecker(mData.medication_supplements!!))
        diet_plan.setText(textChecker(mData.diet_plan!!))
        yoga_meditation_exercise.setText(textChecker(mData.yoga_meditation_exercise!!))
        sleep_advice.setText(textChecker(mData.sleep_advice!!))
        other_lifestyle_advice.setText(textChecker(mData.other_lifestyle_advice!!))
    }
}
