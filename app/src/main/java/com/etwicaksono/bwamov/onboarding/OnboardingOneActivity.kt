package com.etwicaksono.bwamov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.etwicaksono.bwamov.HomeActivity
import com.etwicaksono.bwamov.R
import com.etwicaksono.bwamov.sign.signin.SignInActivity
import com.etwicaksono.bwamov.utils.Preferences
import kotlinx.android.synthetic.main.activity_onboarding_one.*

class OnboardingOneActivity : AppCompatActivity() {

    lateinit var preference: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_one)


        btn_lanjutkan.setOnClickListener({
            var intent = Intent(this@OnboardingOneActivity, OnboardingTwoActivity::class.java)
            startActivity(intent)
        })

        btn_lewati.setOnClickListener({
            preference.setValues("onboarding","1")
            finishAffinity()

            var intent = Intent(this@OnboardingOneActivity, SignInActivity::class.java)
            startActivity(intent)
        })
    }
}