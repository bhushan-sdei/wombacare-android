package com.app.womba.ui.profile.basicProfile

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProviders
import com.app.womba.BR
import com.app.womba.R
import com.app.womba.base.BaseActivity
import com.app.womba.databinding.ActivityBasicProfileBinding
import com.app.womba.dialog.OptionListDialog
import com.app.womba.interfaces.InterfacesCall
import com.app.womba.model.postResponseModel.UpdateProfilePost
import com.app.womba.utils.*
import com.app.womba.utils.customViews.LoadingDialog
import kotlinx.android.synthetic.main.activity_basic_profile.*

class BasicProfileActivity : BaseActivity<ActivityBasicProfileBinding, BasicProfileVM>() {

    override val viewModel: BasicProfileVM
        get() = ViewModelProviders.of(this).get(BasicProfileVM::class.java)
    override val layoutId: Int = R.layout.activity_basic_profile
    override val bindingVariable: Int = BR.viewModel
    override val context: Context = this
    private val ethinticityList: ArrayList<String> = ArrayList()
    private val bloodGroupList: ArrayList<String> = ArrayList()
    var mHeightFeet: Int = 0
    var mHeightInches: Int = 0

    private var LanguageList: ArrayList<OptionsModel> =
        ArrayList()
    private var mSelectedLanguage: OptionsModel = OptionsModel("", "")

    override fun onCreate() {
        setBloodGroupSpinner()
        setData()
    }

    override fun initListeners() {
        mViewModel!!.getLoader().observe(this, androidx.lifecycle.Observer { mData ->
            if (mData) {
                LoadingDialog.getLoader().showLoader(this)
            } else {
                LoadingDialog.getLoader().dismissLoader()
            }
        })

        mViewModel!!.getResponse().observe(this, androidx.lifecycle.Observer { mData ->
            if (mData != null && responseHandler(mData.code, mData.message)) {
                saveUserData(mData)
                finish()
            }
        })

        mViewModel!!.getLanguage().observe(this, androidx.lifecycle.Observer { mData ->
            if (mData != null && responseHandlerNoToast(mData.code, mData.message)) {
                for (model in mData.data!!.data!!) {
                    LanguageList.add(OptionsModel(model.get_id()!!, model.name!!))

                    if (model.get_id().equals(getUserData().data!!.languageId!![0])) {
                        mSelectedLanguage._id = model.get_id()!!
                        mSelectedLanguage.name = model.name!!
                        spinner_language.setText(
                            model.name!!
                        )

                    }

                }
            }
        })

        imgBack.setOnClickListener {
            finish()
        }

        editText_dob.setOnClickListener {
            getBirthDate {
                editText_dob.setText(it)
            }
        }

        til_height.setOnClickListener {
            OptionListDialog(this, R.style.pullBottomfromTop, R.layout.dialog_options,
                getHeight(),
                "SELECT HEIGHT",
                InterfacesCall.Callback { pos ->
                    editText_height.setText(getHeight()[pos].name)
                    mHeightFeet = getHeight()[pos].name.split(" ")[0].toInt()
                    mHeightInches = getHeight()[pos].name.split(" ")[2].toInt()

                    if (mHeightFeet != 0 && editText_weight.text.toString().isNotEmpty()) {
                        editText_bmi.setText(
                            calculateBMI(editText_weight.text.toString().toDouble()).toString()
                        )
                    }

                }).show()
        }

        editText_height.setOnClickListener {
            OptionListDialog(this, R.style.pullBottomfromTop, R.layout.dialog_options,
                getHeight(),
                "SELECT HEIGHT",
                InterfacesCall.Callback { pos ->
                    editText_height.setText(getHeight()[pos].name)
                    mHeightFeet = getHeight()[pos].name.split(" ")[0].toInt()
                    mHeightInches = getHeight()[pos].name.split(" ")[2].toInt()
                    if (mHeightFeet != 0 && editText_weight.text.toString().isNotEmpty()) {
                        editText_bmi.setText(
                            calculateBMI(
                                editText_weight.text.toString().toDouble()
                            ).toString()
                        )
                    }

                }).show()
        }

        spinner_language.setOnClickListener {
            OptionListDialog(this, R.style.pullBottomfromTop, R.layout.dialog_options,
                LanguageList,
                "SELECT LANGUAGE",
                InterfacesCall.Callback { pos ->
                    spinner_language.setText(LanguageList[pos].name)
                    mSelectedLanguage = LanguageList[pos]
                }).show()

        }

        button_next_basic_info.setOnClickListener {
            val gender = if (radioButton_male.isChecked) {
                InterConsts.MALE
            } else {
                InterConsts.FEMALE
            }

            if (validatingRequired()) {
                mViewModel!!.hitUpdateProfile(
                    UpdateProfilePost(
                        getUserData().data!!.get_id()!!,
                        editText_first_name.text.trim().toString(),
                        editText_last_name.text.trim().toString(),
                        gender,
                        convertLocaleDateToUTCDob(editText_dob.text.trim().toString(), this)!!,
                        mHeightFeet.toString(),
                        mHeightInches.toString(),
                        editText_weight.text.trim().toString(),
                        sbloodgroup.selectedItem.toString(),
                        edEthnicity.text.toString(),
                        editText_address.text.trim().toString(),
                        edCountry.text.trim().toString(),
                        edCity.text.trim().toString(),
                        edState.text.trim().toString(),
                        editText_zip.text.trim().toString(),
                        editText_bmi.text.trim().toString(),
                        "", "", mSelectedLanguage._id, "", "7507469605"
                    )
                )
            }

        }

        editText_weight.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (mHeightFeet != 0 && editText_weight.text.toString().isNotEmpty()) {
                    editText_bmi.setText(
                        calculateBMI(editText_weight.text.toString().toDouble()).toString()
                    )
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {

            }
        })

    }

    private fun setBloodGroupSpinner() {

        bloodGroupList.add("O-")
        bloodGroupList.add("O+")
        bloodGroupList.add("A-")
        bloodGroupList.add("A+")
        bloodGroupList.add("B-")
        bloodGroupList.add("B+")
        bloodGroupList.add("AB-")
        bloodGroupList.add("AB+")

        val adapterBloodGroup: ArrayAdapter<String> = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_item,
            bloodGroupList
        )
        adapterBloodGroup.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sbloodgroup.adapter = adapterBloodGroup
    }

    fun setData() {
        val basicInformation = getUserData()
        if (basicInformation.data!!.firstname!!.trim().isNotEmpty())
            editText_first_name.setText(
                basicInformation.data!!.firstname
            )

        if (!basicInformation.data!!.lastname!!.trim()
                .isEmpty()
        ) editText_last_name.setText(
            basicInformation.data!!.lastname
        )
        val gender: String = basicInformation.data!!.gender!!
        if (gender.equals(InterConsts.MALE, ignoreCase = true)) {
            radioButton_male.isChecked = true
        } else if (gender.equals(InterConsts.FEMALE, ignoreCase = true)) {
            radioButton_female.isChecked = true
        }

        editText_address.setText(
            basicInformation.data!!.address
        )

        spinner_language.setText(
            basicInformation.data!!.language
        )
        edPhoneNumber.setText(
            basicInformation.data!!.phone_number
        )

        edState.setText(
            basicInformation.data!!.statename
        )

        edEthnicity.setText(
            basicInformation.data!!.ethnicity
        )

        try {
            mSelectedLanguage._id = basicInformation.data!!.languageId!![0]
            mSelectedLanguage.name = basicInformation.data!!.language!!
        } catch (e: Exception) {

        }

        if (basicInformation.data!!.dob != null && basicInformation.data!!.dob!!.trim()
                .isNotEmpty()
        ) {
            editText_dob.setText(convertDobToLocal(basicInformation.data!!.dob!!))
        }

        if (!basicInformation.data!!.height_feet!!.trim().isNullOrEmpty()) {
            editText_height.setText(basicInformation.data!!.height_feet!!.trim() + " feet " + basicInformation.data!!.height_inch!!.trim() + " inches")
            mHeightFeet = basicInformation.data!!.height_feet!!.trim().toInt()
            if (!basicInformation.data!!.height_inch!!.trim().isNullOrEmpty()) {
                mHeightInches = basicInformation.data!!.height_inch!!.trim().toInt()
            }
        }

        editText_weight.setText(basicInformation.data!!.weight)
        if (mHeightFeet != 0 && editText_weight.text.toString().isNotEmpty()) {
            editText_bmi.setText(
                calculateBMI(editText_weight.text.toString().toDouble()).toString()
            )
        }
        editText_zip.setText(basicInformation.data!!.zip)

        if (basicInformation.data!!.country != null && !TextUtils.isEmpty(
                basicInformation.data!!.country
            )
        ) {
            edCountry.setText(basicInformation.data!!.country.toString())
        }
        if (basicInformation.data!!.city != null && !TextUtils.isEmpty(
                basicInformation.data!!.city
            )
        ) {
            edCity.setText(basicInformation.data!!.city.toString())
        }
        if (!TextUtils.isEmpty(basicInformation.data!!.blood_group)) {
            val pos: Int =
                getSpinnerIndex(basicInformation.data!!.blood_group!!, bloodGroupList)
            if (pos != -1) {
                sbloodgroup.setSelection(pos + 1)
            }
        }

    }

    private fun validatingRequired(): Boolean {
        var message = ""
        if (TextUtils.isEmpty(editText_first_name.text.toString())) {
            message = resources.getString(R.string.firstName)
            til_first_name.error = message
        } else {
            til_first_name.error = null
        }
        if (TextUtils.isEmpty(editText_last_name.text.toString())) {
            message = resources.getString(R.string.lastName)
            til_last_name.error = message
        } else {
            til_last_name.error = null
        }
        if (TextUtils.isEmpty(editText_address.text.toString())) {
            message = resources.getString(R.string.address)
            mViewDataBinding!!.tilAddress.error = message
        } else {
            mViewDataBinding!!.tilAddress.error = null
        }
        if (TextUtils.isEmpty(mViewDataBinding!!.editTextDob.text.toString())) {
            message = resources.getString(R.string.dob)
            mViewDataBinding!!.tilDob.error = message
        } else {
            mViewDataBinding!!.tilDob.error = null
        }
        if (TextUtils.isEmpty(mViewDataBinding!!.editTextHeight.text.toString())) {
            message = resources.getString(R.string.height)
            mViewDataBinding!!.tilHeight.error = message
        } else {
            mViewDataBinding!!.tilHeight.error = null
        }
        if (TextUtils.isEmpty(mViewDataBinding!!.editTextWeight.text.toString())) {
            message = resources.getString(R.string.weight)
            mViewDataBinding!!.tilWeight.error = message
        } else {
            mViewDataBinding!!.tilWeight.error = null
        }

        if (TextUtils.isEmpty(mViewDataBinding!!.edEthnicity.text.toString())) {
            message = resources.getString(R.string.ethnicity)
            mViewDataBinding!!.spinnerEthnicity.error = message
        } else {
            mViewDataBinding!!.spinnerEthnicity.error = null
        }

        /* if (TextUtils.isEmpty( mViewDataBinding!!.editTextBmi.getText().toString())) {
            message = getResources().getString(R.string.bmi);
             mViewDataBinding!!.tilBmi.setError(message);
        } else {
             mViewDataBinding!!.tilBmi.setError(null);
        }*/

//        if (mViewDataBinding!!.spinnerEthnicity.selectedItem == null) {
//            message = resources.getString(R.string.ethnicity)
//            mViewDataBinding!!.spinnerEthnicity.error = message
//        } else {
//            mViewDataBinding!!.spinnerEthnicity.error = null
//        }

        if (mViewDataBinding!!.sbloodgroup.selectedItem == null) {
            message = resources.getString(R.string.blood_group)
            mViewDataBinding!!.sbloodgroup.error = message
        } else {
            mViewDataBinding!!.sbloodgroup.error = null
        }

        if (mSelectedLanguage._id == "") {
            message = "Select Your Language"
            til_Language.error = message
        } else {
            til_Language.error = null
        }


        if (!TextUtils.isEmpty(mViewDataBinding!!.editTextZip.text.toString())) {
            val zipcode: String = mViewDataBinding!!.editTextZip.text.toString()
            if (zipcode.length in 1..5) {
                message = resources.getString(R.string.zipcode)
                mViewDataBinding!!.tilZip.error = message
            } else {
                mViewDataBinding!!.tilZip.error = null
            }
        } else {
            mViewDataBinding!!.tilZip.error = null
        }
        return message.equals("", ignoreCase = true) || message == null
    }

    //Calculate BMI
    private fun calculateBMI(weight: Double): String {
        val weightInKg = weight * 0.45359237
        val totalInches = (mHeightFeet * 12) + mHeightInches
        val heightInMeters = totalInches * 0.0254
        val bmi = weightInKg / (heightInMeters * heightInMeters)
        return String.format("%.2f", bmi)
    }

    fun getSpinnerIndex(
        value: String,
        list: List<String?>
    ): Int {
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



