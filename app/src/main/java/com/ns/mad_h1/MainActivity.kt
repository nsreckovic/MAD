package com.ns.mad_h1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    companion object {
        const val LOGGED_IN_KEY = "loggedInKey"
        const val LOGGED_IN_USERNAME = "loggedInUsername"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val loggedIn = sharedPreferences.getBoolean(LOGGED_IN_KEY, false)
        Timber.e(loggedIn.toString())
        if (loggedIn) {
            val intent = Intent(this, ArticleActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        finish()
    }

}
