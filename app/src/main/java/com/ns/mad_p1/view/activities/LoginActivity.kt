package com.ns.mad_p1.view.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ns.mad_p1.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(R.layout.activity_login) {

    companion object {
        const val default_pin = 1234
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initListeners()
    }

    private fun initListeners() {
        signinBtn.setOnClickListener {
            val editor = getSharedPreferences(packageName, Context.MODE_PRIVATE).edit()
            val name = nameEt.text.toString()
            val surname = surnameEt.text.toString()
            val hospital = hospitalEt.text.toString()
            val pin_str = pinEt.text.toString()

            if (name == "" || surname == "" || hospital == "" || pin_str == "") {
                Toast.makeText(this, R.string.input_text_error, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val pin = Integer.parseInt(pin_str)

            if (pin_str.length != 4) {
                Toast.makeText(this, R.string.input_pin_lenght_error, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pin == default_pin) {
                editor.putBoolean(SplashActivity.LOGGED_IN, true)
                editor.putString(SplashActivity.LOGGED_IN_USER_NAME, name)
                editor.putString(SplashActivity.LOGGED_IN_USER_SURNAME, surname)
                editor.putString(SplashActivity.LOGGED_IN_USER_HOSPITAL, hospital)
                editor.commit()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, R.string.input_pin_error, Toast.LENGTH_SHORT).show()
            }

        }
    }
}
