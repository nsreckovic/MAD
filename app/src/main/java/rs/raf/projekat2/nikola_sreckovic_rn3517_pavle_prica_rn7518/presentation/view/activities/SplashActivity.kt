package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.MainActivity
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.R

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    companion object {
        const val LOGGED_IN = "loggedIn"
        const val LOGGED_IN_USERNAME = "loggedInUsername"
        const val LOGGED_IN_PASSWORD = "loggedInPassword"
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
