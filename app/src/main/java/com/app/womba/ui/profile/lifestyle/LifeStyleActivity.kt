package com.app.womba.ui.profile.lifestyle

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.womba.BR
import com.app.womba.R
import com.app.womba.base.BaseActivity
import com.app.womba.base.BaseModel
import com.app.womba.databinding.ActivityLifeStyleBinding
import com.app.womba.model.LifeStylePost
import com.app.womba.model.PatientLifeStyleModel
import com.app.womba.utils.customViews.LoadingDialog
import com.app.womba.utils.getUserData
import com.app.womba.utils.responseHandlerNoToast
import com.app.womba.utils.responseHandlerToast
import kotlinx.android.synthetic.main.activity_life_style.*

class LifeStyleActivity : BaseActivity<ActivityLifeStyleBinding, LifeStyleVM>() {

    override val viewModel: LifeStyleVM
        get() = ViewModelProviders.of(this).get(LifeStyleVM::class.java)
    override val layoutId: Int = R.layout.activity_life_style
    override val bindingVariable: Int = BR.viewModel
    override val context: Context = this

    var alcoholConsumption: String = ""
    var annualPhysical: String = ""
    var caffeine: String = ""
    var diabetes: String = ""
    var dietaryRestrictions: String = ""
    var drugsOrMedication: String = ""
    var exercise: String = ""
    var fitnessTracker: String = ""
    var patientId: String = ""
    var sexEngageTime: String = ""
    var sleep: String = ""
    var smoked: String = ""
    var tobaccoConsumption: String = ""

    override fun onCreate() {
        patientId = getUserData().data!!.get_id()!!
        setSpinner()
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
                    finish()
                }
            })

        mViewModel!!.getPatientLifeStyleResponse().observe(this,
            Observer<PatientLifeStyleModel> { mData ->
                if (mData != null && responseHandlerNoToast(mData.code, mData.message)) {
                    if (mData.data.isNullOrEmpty() && mData.data!!.size > 0)
                        setData(mData.data!![0])
                }
            })

        mViewModel!!.getPatientLifeStyle()

        button_next_lifestyle.setOnClickListener {
            mViewModel!!.savePatientLifeStyle(
                LifeStylePost(
                    patientId,
                    alcoholConsumption,
                    annualPhysical,
                    caffeine,
                    diabetes,
                    dietaryRestrictions,
                    drugsOrMedication,
                    exercise,
                    fitnessTracker,
                    sexEngageTime,
                    sleep,
                    smoked,
                    tobaccoConsumption
                )
            )

        }

        imgBack.setOnClickListener {
            finish()
        }

    }

    fun setData(lifestyle: PatientLifeStyleModel.DataBean) {
        if (lifestyle != null) {
            var pos = 0
            pos = getSpinnerIndex(
                lifestyle.dietaryRestrictions!!,
                resources.getStringArray(R.array.dietary_restrictons)
            )
            if (pos != -1) {
                mViewDataBinding!!.spinnerDietaryRestrictions.setSelection(pos + 1)
            }
            pos = getSpinnerIndex(
                lifestyle.alcoholConsumption!!,
                resources.getStringArray(R.array.alcohol_consumption)
            )
            if (pos != -1) {
                mViewDataBinding!!.spinnerAlcoholConsumption.setSelection(pos + 1)
            }
            pos = getSpinnerIndex(
                lifestyle.smoked!!,
                resources.getStringArray(R.array.smoke_tobacco)
            )
            if (pos != -1) {
                mViewDataBinding!!.spinnerProvider.setSelection(pos + 1)
            }
            pos = getSpinnerIndex(
                lifestyle.sexEngageTime!!,
                resources.getStringArray(R.array.intercourse)
            )
            if (pos != -1) {
                mViewDataBinding!!.spinnerSex.setSelection(pos + 1)
            }
            pos = getSpinnerIndex(
                lifestyle.caffeine!!,
                resources.getStringArray(R.array.caffine_consumption)
            )
            if (pos != -1) {
                mViewDataBinding!!.spinnerCaffeineConsume.setSelection(pos + 1)
            }
            pos = getSpinnerIndex(
                lifestyle.diabetes!!,
                resources.getStringArray(R.array.yes_no)
            )
            if (pos != -1) {
                mViewDataBinding!!.spinnerMaritalStatus.setSelection(pos + 1)
            }

            pos = getSpinnerIndex(
                lifestyle.annualPhysical!!,
                resources.getStringArray(R.array.yes_no)
            )
            if (pos != -1) {
                mViewDataBinding!!.spinnerLivingArrangements.setSelection(pos + 1)
            }

//         ----

            pos = getSpinnerIndex(
                lifestyle.drugsOrMedication!!,
                resources.getStringArray(R.array.yes_no)
            )
            if (pos != -1) {
                mViewDataBinding!!.spinnerDrug.setSelection(pos + 1)
            }

            pos = getSpinnerIndex(
                lifestyle.sleep!!,
                resources.getStringArray(R.array.sleep)
            )
            if (pos != -1) {
                mViewDataBinding!!.spinnerWeekendSleep.setSelection(pos + 1)
            }

            pos = getSpinnerIndex(
                lifestyle.exercise!!,
                resources.getStringArray(R.array.yes_no)
            )
            if (pos != -1) {
                mViewDataBinding!!.spinnerExcercise.setSelection(pos + 1)
            }

            pos = getSpinnerIndex(
                lifestyle.fitnessTracker!!,
                resources.getStringArray(R.array.yes_no)
            )
            if (pos != -1) {
                mViewDataBinding!!.spinnerFitnessTracker.setSelection(pos + 1)
            }

        }

    }


    private fun setSpinner() {

        val adapterDietary: ArrayAdapter<String> = ArrayAdapter<String>(
            mContext, android.R.layout.simple_spinner_item,
            mContext.resources.getStringArray(R.array.dietary_restrictons)
        )
        adapterDietary.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mViewDataBinding!!.spinnerDietaryRestrictions.adapter = adapterDietary
        mViewDataBinding!!.spinnerDietaryRestrictions.onItemSelectedListener = object :
            OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val `object` = parent.selectedItem
                if (`object` != null) {
                    dietaryRestrictions = `object`.toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        val adapterAlcohol: ArrayAdapter<String> = ArrayAdapter<String>(
            mContext,
            android.R.layout.simple_spinner_item,
            mContext.resources.getStringArray(R.array.alcohol_consumption)
        )
        adapterAlcohol.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mViewDataBinding!!.spinnerAlcoholConsumption.adapter = adapterAlcohol
        mViewDataBinding!!.spinnerAlcoholConsumption.onItemSelectedListener = object :
            OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val `object` = parent.selectedItem
                if (`object` != null) {
                    alcoholConsumption = `object`.toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        val adapterSmoke: ArrayAdapter<String> = ArrayAdapter<String>(
            mContext,
            android.R.layout.simple_spinner_item,
            mContext.resources.getStringArray(R.array.smoke_tobacco)
        )
        adapterSmoke.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mViewDataBinding!!.spinnerProvider.adapter = adapterSmoke
        mViewDataBinding!!.spinnerProvider.onItemSelectedListener = object :
            OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val `object` = parent.selectedItem
                if (`object` != null) {
                    smoked = `object`.toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        val adapterIntercourse: ArrayAdapter<String> = ArrayAdapter<String>(
            mContext,
            android.R.layout.simple_spinner_item,
            mContext.resources.getStringArray(R.array.intercourse)
        )
        adapterIntercourse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mViewDataBinding!!.spinnerSex.adapter = adapterIntercourse
        mViewDataBinding!!.spinnerSex.onItemSelectedListener = object :
            OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val `object` = parent.selectedItem
                if (`object` != null) {
                    sexEngageTime = `object`.toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val adapterMaritialStatus: ArrayAdapter<String> = ArrayAdapter<String>(
            mContext,
            android.R.layout.simple_spinner_item,
            mContext.resources.getStringArray(R.array.yes_no)
        )
        adapterMaritialStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mViewDataBinding!!.spinnerMaritalStatus.adapter = adapterMaritialStatus
        mViewDataBinding!!.spinnerMaritalStatus.onItemSelectedListener = object :
            OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val `object` = parent.selectedItem
                if (`object` != null) {
                    diabetes = `object`.toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        val adapterCaffine: ArrayAdapter<String> = ArrayAdapter<String>(
            mContext,
            android.R.layout.simple_spinner_item,
            mContext.resources.getStringArray(R.array.caffine_consumption)
        )
        adapterCaffine.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mViewDataBinding!!.spinnerCaffeineConsume.adapter = adapterCaffine
        mViewDataBinding!!.spinnerCaffeineConsume.onItemSelectedListener = object :
            OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val `object` = parent.selectedItem
                if (`object` != null) {
                    caffeine = `object`.toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        val adapterLiving: ArrayAdapter<String> = ArrayAdapter<String>(
            mContext,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.yes_no)
        )
        adapterLiving.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mViewDataBinding!!.spinnerLivingArrangements.adapter = adapterLiving
        mViewDataBinding!!.spinnerLivingArrangements.onItemSelectedListener = object :
            OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val `object` = parent.selectedItem
                if (`object` != null) {
                    annualPhysical = `object`.toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val adapterregularexercise: ArrayAdapter<String> = ArrayAdapter<String>(
            mContext,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.yes_no)
        )

        adapterregularexercise.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mViewDataBinding!!.spinnerExcercise.adapter = adapterregularexercise
        mViewDataBinding!!.spinnerExcercise.onItemSelectedListener = object :
            OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                val `object` = parent.selectedItem
                if (`object` != null) {
                    exercise = `object`.toString()
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val drugAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            mContext,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.yes_no)
        )

        drugAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mViewDataBinding!!.spinnerDrug.adapter = drugAdapter
        mViewDataBinding!!.spinnerDrug.onItemSelectedListener = object :
            OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                val `object` = parent.selectedItem
                if (`object` != null) {
                    drugsOrMedication = `object`.toString()
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val fitnesTrackerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            mContext,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.yes_no)
        )

        fitnesTrackerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mViewDataBinding!!.spinnerFitnessTracker.adapter = fitnesTrackerAdapter
        mViewDataBinding!!.spinnerFitnessTracker.onItemSelectedListener = object :
            OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                val `object` = parent.selectedItem
                if (`object` != null) {
                    fitnessTracker = `object`.toString()
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val weekendSleepAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            mContext,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.sleep)
        )

        weekendSleepAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mViewDataBinding!!.spinnerWeekendSleep.adapter = weekendSleepAdapter
        mViewDataBinding!!.spinnerWeekendSleep.onItemSelectedListener = object :
            OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                val `object` = parent.selectedItem
                if (`object` != null) {
                    sleep = `object`.toString()
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }

    fun getSpinnerIndex(value: String, list: Array<String?>): Int {
        var pos = -1
        for (i in list.indices) {
            if (value.equals(list[i], ignoreCase = true)) {
                pos = i
                return pos
            }
        }
        return pos
    }


}
