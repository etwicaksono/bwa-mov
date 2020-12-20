package com.etwicaksono.bwamov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.etwicaksono.bwamov.onboarding.OnboardingOneActivity
import com.etwicaksono.bwamov.sign.signin.SignInActivity
import com.etwicaksono.bwamov.utils.Preferences

class SplashScreenActivity : AppCompatActivity() {

    lateinit var preference: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        preference = Preferences(this)

            var handler = Handler()
            handler.postDelayed({

                if (preference.getValues("onboarding")
                        .equals("1") && preference.getValues("status").equals("1")
                ) {
                    var intent = Intent(this@SplashScreenActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } else if (preference.getValues("onboarding")
                        .equals("1") && preference.getValues("status") != "1"
                ) {
                    var intent = Intent(this@SplashScreenActivity, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    var intent =
                        Intent(this@SplashScreenActivity, OnboardingOneActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }, 2000)

    }
}
