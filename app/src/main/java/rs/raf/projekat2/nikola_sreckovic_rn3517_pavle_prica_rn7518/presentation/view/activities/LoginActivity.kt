package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.MainActivity
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.R

class LoginActivity : AppCompatActivity(R.layout.activity_login) {

    companion object {
        const val default_username = "test"
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
        login_LogIn_Btn.setOnClickListener {
            val editor = getSharedPreferences(packageName, Context.MODE_PRIVATE).edit()
            val username = login_Username_Et.text.toString()
            val pin_str = login_Pin_Et.text.toString()

            if (username.isEmpty() || pin_str.isEmpty()) {
                Toast.makeText(this, R.string.input_text_error, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val pin = Integer.parseInt(pin_str)

            if (pin_str.length != 4) {
                Toast.makeText(this, R.string.login_input_pin_length_error, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (username == default_username) {
                if (pin == default_pin) {
                    editor.putBoolean(SplashActivity.LOGGED_IN, true)
                    editor.putString(SplashActivity.LOGGED_IN_USERNAME, username)
                    editor.commit()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, R.string.login_input_pin_error, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, R.string.login_input_username_error, Toast.LENGTH_SHORT).show()
            }

        }
    }
}
