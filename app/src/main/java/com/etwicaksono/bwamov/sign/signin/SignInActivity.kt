package com.etwicaksono.bwamov.sign.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.etwicaksono.bwamov.HomeActivity
import com.etwicaksono.bwamov.R
import com.etwicaksono.bwamov.sign.SignUpActivity
import com.etwicaksono.bwamov.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    lateinit var iUsername: String
    lateinit var iPassword: String

    lateinit var mDatabase: DatabaseReference
    var url = "https://bwa-mov-10a11-default-rtdb.firebaseio.com/"

    lateinit var preference: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mDatabase = FirebaseDatabase.getInstance(url).getReference("User")
        preference = Preferences(this)

        preference.setValues("onboarding", "1")

        btn_sign_in.setOnClickListener({
            iUsername = et_username.text.toString()
            iPassword = et_password.text.toString()

            if (iUsername.equals("")) {
                et_username.error = "Silahkan tulis username anda"
                et_username.requestFocus()
            } else if (iPassword.equals("")) {
                et_password.error = "Silahkan tulis password anda"
                et_password.requestFocus()
            } else {
                pushLogin(iUsername, iPassword)
            }
        })

        btn_sign_up.setOnClickListener({
            var goSignUp = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(goSignUp)
        })
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignInActivity, databaseError.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)

                if (user == null) {
                    Toast.makeText(this@SignInActivity, "User tidak ditemukan", Toast.LENGTH_LONG)
                        .show()
                } else {
                    if (user.password.equals(iPassword)) {

                        preference.setValues("nama", user.nama.toString())
                        preference.setValues("username", user.username.toString())
                        preference.setValues("url", user.url.toString())
                        preference.setValues("email", user.email.toString())
                        preference.setValues("saldo", user.saldo.toString())
                        preference.setValues("status", "1")

                        var intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@SignInActivity,
                            "Password anda salah",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })

    }
}