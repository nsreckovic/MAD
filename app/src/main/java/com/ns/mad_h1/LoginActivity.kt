package com.ns.mad_h1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(R.layout.activity_login) {

    companion object {
        const val default_username = "Nikola"
        const val default_password = "testing321"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBtn.setOnClickListener {
            val editor = getSharedPreferences(packageName, Context.MODE_PRIVATE).edit()
            val username = usernameEt.text.toString()
            val password = passwordEt.text.toString()
            if (username == default_username) {
                if (password == default_password) {
                    Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show()
                    editor.putBoolean(MainActivity.LOGGED_IN_KEY, true)
                    editor.putString(MainActivity.LOGGED_IN_USERNAME, username)
                    editor.commit()
                    val intent = Intent(this, ArticleActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, R.string.password_error, Toast.LENGTH_SHORT).show()
                    passwordEt.setText("")
                }
            } else {
                Toast.makeText(this, R.string.username_error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
