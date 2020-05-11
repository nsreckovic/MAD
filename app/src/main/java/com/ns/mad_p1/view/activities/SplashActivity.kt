package com.ns.mad_p1.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    companion object {
        const val LOGGED_IN = "loggedIn"
        const val LOGGED_IN_USER_NAME = "loggedInUserName"
        const val LOGGED_IN_USER_SURNAME = "loggedInUserSurname"
        const val LOGGED_IN_USER_HOSPITAL = "loggedInUserHospital"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val loggedIn = sharedPreferences.getBoolean(LOGGED_IN, false)

        if (loggedIn) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        finish()
    }
}