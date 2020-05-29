package com.app.womba.ui.profile.healthProfile

import android.app.Dialog
import android.content.Context
import android.text.InputType
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.womba.BR
import com.app.womba.R
import com.app.womba.adapter.*
import com.app.womba.base.BaseActivity
import com.app.womba.base.BaseModel
import com.app.womba.databinding.ActivityHealthProfileBinding
import com.app.womba.interfaces.HealthInterface
import com.app.womba.model.healthProfile.HealthProfileModel
import com.app.womba.model.postResponseModel.*
import com.app.womba.utils.customViews.LoadingDialog
import com.app.womba.utils.getBirthDate
import com.app.womba.utils.getDate
import com.app.womba.utils.getUserData
import com.app.womba.utils.responseHandlerToast
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_health_profile.*


class HealthProfileActivity : BaseActivity<ActivityHealthProfileBinding, HealthProfileVM>() {

    override val viewModel: HealthProfileVM
        get() = ViewModelProviders.of(this).get(HealthProfileVM::class.java)
    override val layoutId: Int = R.layout.activity_health_profile
    override val bindingVariable: Int = BR.viewModel
    override val context: Context = this

    var mConditionsAndSymptomsList = ArrayList<Condition>()
    var mMedicationsList = ArrayList<Medications>()
    var mAllergiesList = ArrayList<Alergies>()
    var mTreatmentsList = ArrayList<Treatment>()
    var mVaccinationsList = ArrayList<Immunization>()
    var mCovidList = ArrayList<MedicalCovid>()

    override fun onCreate() {
        switch_covid.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                textInputLayout_hospitalized.visibility = View.VISIBLE
                textInputLayout_treatment.visibility = View.VISIBLE
            } else {
                textInputLayout_hospitalized.visibility = View.GONE
                textInputLayout_treatment.visibility = View.GONE
            }
        }

        imageButton_covid_save.setOnClickListener {
            var redio = ""
            redio = if (switch_covid.isChecked) {
                "Yes"
            } else {
                "No"
            }
            mViewModel!!.addMedicalCovid(
                MedicalCovidPost(
                    getUserData().data!!.get_id()!!,
                    redio,
                    textInputEditText_hospital.text.toString(),
                    textInputEditText_treatment.text.toString()
                )
            )
        }
    }

    private fun setCovidData() {
        switch_covid.isChecked = mCovidList[0].AreyouInfected == "Yes"
        textInputEditText_hospital.setText(mCovidList[0].HospitalName)
        textInputEditText_treatment.setText(mCovidList[0].covidTreatment)
    }

    override fun initListeners() {
        mViewModel!!.getLoader().observe(this,
            Observer { mData ->
                if (mData) {
                    LoadingDialog.getLoader().showLoader(this)
                } else {
                    LoadingDialog.getLoader().dismissLoader()
                }
            })

        mViewModel!!.getResponse().observe(this,
            Observer<BaseModel> { mData ->
                if (mData != null && responseHandlerToast(mData.code, mData.message)) {
                }
            })

       mViewModel!!.getDeleteResponse().observe(this,
            Observer<BaseModel> { mData ->
                if (mData != null && responseHandlerToast(mData.code, mData.message)) {
                    mViewModel!!.getallMedicalData()
                }
            })

        mViewModel!!.getMedicalData().observe(this,
            Observer<HealthProfileModel> { mData ->
                if (mData != null && responseHandlerToast(mData.code, mData.message)) {
                    mConditionsAndSymptomsList = mData.data!!.conditionsData!!
                    mMedicationsList = mData.data!!.medicationData!!
                    mAllergiesList = mData.data!!.allergiesData!!
                    mVaccinationsList = mData.data!!.immunizationsData!!
                    mTreatmentsList = mData.data!!.treatmentsData!!
                    mCovidList = mData.data!!.covidsData!!


                    setConditionAdapter()
                    setMedicationAdapter()
                    setAllergiesAdapter()
                    setTreatmentAdapter()
                    setVaccinationAdapter()
                    if (mCovidList.isNotEmpty())
                        setCovidData()
                }
            })

        mViewModel!!.getallMedicalData()
        setConditionData()
        setMedicationData()
        setAllergiesData()
        setTreatmentData()
        setVaccinationData()

        imgBack.setOnClickListener {
            finish()
        }
    }

    private fun setConditionData() {
        imageButton_condition.setOnClickListener {
            val dialog = Dialog(this@HealthProfileActivity)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.add_condition_symptom)
            val window = dialog.window
            window!!.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            window.setGravity(Gravity.CENTER)
            //            conditionSymtom = ConditionSymtom()
            val textInputLayout =
                dialog.findViewById<View>(R.id.til_conditions_symptoms) as TextInputLayout
            val mCondition =
                dialog.findViewById<View>(R.id.editText_conditions_symptoms) as EditText
            val mNote =
                dialog.findViewById<View>(R.id.editText_notes) as EditText
            val mAdd =
                dialog.findViewById<Button>(R.id.button_add)
            val rdGroup =
                dialog.findViewById<RadioGroup>(R.id.radioGroup_conditions_symptoms)
            val radioButton =
                dialog.findViewById<RadioButton>(R.id.radioButton_have)
            dialog.show()
            mAdd.setOnClickListener {
                var redio = ""
                redio = if (radioButton.isChecked) {
                    "Yes"
                } else {
                    "No"
                }
                val conditionSymtom = ConditionPost(
                    getUserData().data!!.get_id()!!,
                    mCondition.text.toString(),
                    redio,
                    mNote.text.toString()
                )

                if (TextUtils.isEmpty(
                        mCondition.text.toString().trim { it <= ' ' }
                    )
                ) {
                    textInputLayout.error = "Condition Name Required"
                } else {
                    mConditionsAndSymptomsList.add(
                        Condition(
                            getUserData().data!!.get_id()!!,
                            "",
                            mCondition.text.toString(),
                            redio,
                            mNote.text.toString()
                        )
                    )
                    mViewModel!!.addMedicalConditions(conditionSymtom)
                    setConditionAdapter()
                    textInputLayout.error = null
                    dialog.dismiss()
                }
            }
        }
    }

    private fun setConditionAdapter() {
        recyclerView_condition.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView_condition!!.adapter = ConditionsAndSymptomsAdapter(
            mContext,
            mConditionsAndSymptomsList,
            object : HealthInterface {
                override fun onDeleteClick(position: Int) {
                    mViewModel!!.deleteMedicalRecord(
                        DeleteMedical(
                            getUserData().data!!.get_id()!!,
                            mConditionsAndSymptomsList[position]._id,
                            "condition"
                        )
                    )
                    mConditionsAndSymptomsList.removeAt(position)
                    setConditionAdapter()
                }

                override fun onEditClick(position: Int) {
                }
            })
        recyclerView_condition.isNestedScrollingEnabled = false
    }


    private fun setMedicationData() {
        textView_medication.text = resources.getString(R.string.healthProfile_title2)

        imageButton_medication.setOnClickListener(View.OnClickListener {
            val dialog = Dialog(this@HealthProfileActivity)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.add_medication)
            val window = dialog.window
            window!!.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            window.setGravity(Gravity.CENTER)
//            val patientMedication = PatientMedication()

            val mMedication =
                dialog.findViewById<View>(R.id.editText_search_medication) as EditText

            val mName =
                dialog.findViewById<View>(R.id.editText_name) as EditText

            val mForm =
                dialog.findViewById<View>(R.id.editText_form) as EditText

            val mDosage =
                dialog.findViewById<View>(R.id.editText_dosage) as EditText

            val mUnit =
                dialog.findViewById<View>(R.id.editText_unit) as EditText

            val mDuration =
                dialog.findViewById<View>(R.id.editText_duration) as EditText

            val mPillsLeft =
                dialog.findViewById<View>(R.id.editText_pills_left) as EditText

            val mFromDate =
                dialog.findViewById<View>(R.id.editText_from_date) as EditText
            mFromDate.inputType = InputType.TYPE_NULL

            val mToDate =
                dialog.findViewById<View>(R.id.editText_to_date) as EditText
            mToDate.inputType = InputType.TYPE_NULL

            val mDate =
                dialog.findViewById<View>(R.id.editText_date) as EditText
            mDate.inputType = InputType.TYPE_NULL

            val mInstructions =
                dialog.findViewById<View>(R.id.editText_instruction) as EditText

            val mReminder =
                dialog.findViewById<View>(R.id.editText_reminder) as EditText

            val time =
                dialog.findViewById<View>(R.id.editText_time) as EditText

            val radioButton =
                dialog.findViewById<RadioButton>(R.id.radioButton_taking)

            val radioButton1 =
                dialog.findViewById<RadioButton>(R.id.radioButton_not_taking)

            val switch_refil_reminder =
                dialog.findViewById<Switch>(R.id.switch_refil_reminder)

            val mAdd =
                dialog.findViewById<Button>(R.id.button_add)
            dialog.show()

            mFromDate.setOnClickListener {
                getDate {
                    mFromDate.setText(it)
                }
            }

            mToDate.setOnClickListener {
                getDate {
                    mToDate.setText(it)
                }
            }

            mDate.setOnClickListener {
                getDate {
                    mDate.setText(it)
                }
            }

            mAdd.setOnClickListener {
                if (!TextUtils.isEmpty(
                        mName.text.toString().trim { it <= ' ' }
                    )
                ) {
                    var status = if (radioButton.isChecked) {
                        "Yes"
                    } else {
                        "No"
                    }
                    var refilReminder = if (switch_refil_reminder.isChecked) {
                        "Yes"
                    } else {
                        "NO"
                    }

                    val patientMedication = MedicationsPost(
                        getUserData().data!!.get_id()!!,
                        mName.text.toString(),
                        mForm.text.toString(),
                        status,
                        mDosage.text.toString().toInt(),
                        mUnit.text.toString(),
                        mFromDate.text.toString(),
                        mToDate.text.toString(),
                        mPillsLeft.text.toString().toInt(),
                        mInstructions.text.toString(),
                        mDate.text.toString(),
                        refilReminder,
                        time.text.toString()
                    )
                    mMedicationsList.add(
                        Medications(
                            "",
                            getUserData().data!!.get_id()!!,
                            mName.text.toString(),
                            mForm.text.toString(),
                            status,
                            mDosage.text.toString().toInt(),
                            mUnit.text.toString(),
                            mFromDate.text.toString(),
                            mToDate.text.toString(),
                            mPillsLeft.text.toString().toInt(),
                            mInstructions.text.toString(),
                            mDate.text.toString(),
                            refilReminder,
                            time.text.toString()
                        )
                    )
                    mViewModel!!.addMedicalMedications(patientMedication)
                    setMedicationAdapter()
                    dialog.dismiss()

                } else {
                    Toast.makeText(
                        this@HealthProfileActivity,
                        "Please provide all the required details",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            mDate.setOnClickListener {
                getBirthDate {
                    mDate.setText(it)
                }
            }
        })
    }

    private fun setMedicationAdapter() {
        recyclerView_medication.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView_medication!!.adapter = MedicationAdapter(mContext, mMedicationsList,
            object : HealthInterface {
                override fun onDeleteClick(position: Int) {
                    mViewModel!!.deleteMedicalRecord(
                        DeleteMedical(
                            getUserData().data!!.get_id()!!,
                            mMedicationsList[position]._id, "Medications"
                        )
                    )
                    mMedicationsList.removeAt(position)
                    setMedicationAdapter()
                }

                override fun onEditClick(position: Int) {
                }

            })
        recyclerView_medication.isNestedScrollingEnabled = false

    }


    private fun setAllergiesData() {
        imageButton_allergies.setOnClickListener(View.OnClickListener {
            val dialog = Dialog(this@HealthProfileActivity)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.add_allergy)
            val window = dialog.window
            window!!.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            window.setGravity(Gravity.CENTER)
            val mAllergy =
                dialog.findViewById<View>(R.id.editText_allergy) as EditText
            val mNote =
                dialog.findViewById<View>(R.id.editText_notes) as EditText
            val mAdd =
                dialog.findViewById<Button>(R.id.button_add)
            val radioButton =
                dialog.findViewById<RadioButton>(R.id.radioButton_have)
            dialog.show()
            mAdd.setOnClickListener {
                if (mAllergy.text != null) {
                    var status = if (radioButton.isChecked) {
                        "Yes"
                    } else {
                        "No"
                    }
                    val patientAllergies = AlergiesPost(
                        getUserData().data!!.get_id()!!,
                        mAllergy.text.toString(), status, mNote.text.toString()
                    )

                    mAllergiesList.add(
                        Alergies(
                            "",
                            getUserData().data!!.get_id()!!,
                            mAllergy.text.toString(), status, mNote.text.toString()
                        )
                    )

                    mViewModel!!.addMedicalAlergies(
                        patientAllergies
                    )
                    setAllergiesAdapter()
                    dialog.dismiss()
                }
            }
        })
    }

    private fun setAllergiesAdapter() {
        recyclerView_allergies.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView_allergies!!.adapter = AllergiesAdapter(mContext, mAllergiesList,
            object : HealthInterface {
                override fun onDeleteClick(position: Int) {
                    mViewModel!!.deleteMedicalRecord(
                        DeleteMedical(
                            getUserData().data!!.get_id()!!,
                            mAllergiesList[position]._id, "allergies"
                        )
                    )
                    mAllergiesList.removeAt(position)
                    setAllergiesAdapter()
                }

                override fun onEditClick(position: Int) {
                }

            })
        recyclerView_allergies.isNestedScrollingEnabled = false
    }


    private fun setTreatmentData() {

        imageButton_treatment.setOnClickListener {
            val dialog = Dialog(context)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.add_treatment)
            val window = dialog.window
            window!!.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            window.setGravity(Gravity.CENTER)
            //            val patientTreatment = PatientTreatment()
            val inputLayout =
                dialog.findViewById<View>(R.id.textInputLayout_treatment) as TextInputLayout
            val mTreatment =
                dialog.findViewById<View>(R.id.textInputEditText_treatment) as EditText
            val mDate =
                dialog.findViewById<View>(R.id.textInputEditText_date) as EditText
            mDate.inputType = InputType.TYPE_NULL
            val mNote =
                dialog.findViewById<View>(R.id.textInputEditText_note) as EditText
            val mAdd =
                dialog.findViewById<Button>(R.id.button_add)
            dialog.show()
            mAdd.setOnClickListener {
                if (!TextUtils.isEmpty(mTreatment.text.toString())) {
                    inputLayout.error = null

                    mDate.setOnClickListener {
                        getDate {
                            mDate.setText(it)
                        }
                    }
                    var patientTreatment = TreatmentPost(
                        getUserData().data!!.get_id()!!,
                        mTreatment.text.toString(), mDate.text.toString()
                    )

                    mTreatmentsList.add(
                        Treatment(
                            "",
                            getUserData().data!!.get_id()!!,
                            mTreatment.text.toString(), mDate.text.toString()
                        )
                    )
                    mViewModel!!.addMedicalTreatments(
                        patientTreatment
                    )
                    setTreatmentAdapter()
                    dialog.dismiss()
                } else {
                    inputLayout.error = "Treatment name required"
                }
            }
            mDate.setOnClickListener {
                getBirthDate {
                    mDate.setText(it)
                }

            }
        }
    }

    private fun setTreatmentAdapter() {
        recyclerView_treatment.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView_treatment!!.adapter = TreatmentAdapter(mContext, mTreatmentsList,
            object : HealthInterface {
                override fun onDeleteClick(position: Int) {
                    mViewModel!!.deleteMedicalRecord(
                        DeleteMedical(
                            getUserData().data!!.get_id()!!,
                            mTreatmentsList[position]._id, "treatment"
                        )
                    )
                    mTreatmentsList.removeAt(position)
                    setTreatmentAdapter()
                }

                override fun onEditClick(position: Int) {
                }

            })
        recyclerView_treatment.isNestedScrollingEnabled = false
    }

    private fun setVaccinationData() {
        imageButton_Immunizations.setOnClickListener(View.OnClickListener {
            val dialog = Dialog(this@HealthProfileActivity)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.add_vacination)
            val window = dialog.window
            window!!.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            window.setGravity(Gravity.CENTER)
//            val patientImmunization = PatientImmunization()
            val adapter = ArrayAdapter(
                this@HealthProfileActivity,
                android.R.layout.simple_dropdown_item_1line,
                resources.getStringArray(R.array.vaccinations)
            )
            val textInputLayout =
                dialog.findViewById<View>(R.id.textInputLayout_vacination) as TextInputLayout
            val mVacination =
                dialog.findViewById<View>(R.id.textInputEditText_vacination) as AutoCompleteTextView
            val mReactions =
                dialog.findViewById<View>(R.id.textInputEditText_reaction) as AutoCompleteTextView
            mVacination.setAdapter(adapter)
            val mAdd =
                dialog.findViewById<Button>(R.id.button_add)
            dialog.show()
            mAdd.setOnClickListener {
                if (!TextUtils.isEmpty(mVacination.text.toString().trim { it <= ' ' })) {
                    textInputLayout.error = null
                    var patientImmunization = ImmunizationPost(
                        getUserData().data!!.get_id()!!,
                        mVacination.text.toString(),
                        mReactions.text.toString()
                    )
                    mVaccinationsList.add(
                        Immunization(
                            "",
                            getUserData().data!!.get_id()!!,
                            mVacination.text.toString(),
                            mReactions.text.toString()
                        )
                    )
                    mViewModel!!.addMedicalImmunization(
                        patientImmunization
                    )
                    setVaccinationAdapter()
                    dialog.dismiss()
                } else {
                    textInputLayout.error = "Please provide vaccination name"
                }
            }
        })
    }

    private fun setVaccinationAdapter() {
        recyclerView_Immunizations.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView_Immunizations!!.adapter = VaccinationsAdapter(mContext, mVaccinationsList,
            object : HealthInterface {
                override fun onDeleteClick(position: Int) {
                    mViewModel!!.deleteMedicalRecord(
                        DeleteMedical(
                            getUserData().data!!.get_id()!!,
                            mVaccinationsList[position]._id, "immunization"
                        )
                    )
                    mVaccinationsList.removeAt(position)
                    setVaccinationAdapter()
                }

                override fun onEditClick(position: Int) {
                }
            })

        recyclerView_Immunizations.isNestedScrollingEnabled = false
    }


}
