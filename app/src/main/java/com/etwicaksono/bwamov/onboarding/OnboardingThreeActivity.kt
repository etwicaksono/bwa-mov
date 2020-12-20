package com.etwicaksono.bwamov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.etwicaksono.bwamov.R
import com.etwicaksono.bwamov.sign.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_onboarding_three.*

class OnboardingThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_three)

        btn_lanjutkan.setOnClickListener({
            finishAffinity()

            var intent = Intent(this@OnboardingThreeActivity, SignInActivity::class.java)
            startActivity(intent)
        })
    }
}