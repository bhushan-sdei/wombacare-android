package com.app.womba.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.womba.R
import com.app.womba.ui.profile.registerProfile.RegisterProfileActivity
import com.app.womba.utils.navigateClass
import kotlinx.android.synthetic.main.activity_login_complete.*

class LoginCompleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_complete)

        btnProfile.setOnClickListener {
            navigateClass<RegisterProfileActivity>()
        }
    }
}
