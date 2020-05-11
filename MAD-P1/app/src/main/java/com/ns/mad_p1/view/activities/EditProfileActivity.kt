package com.ns.mad_p1.view.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ns.mad_p1.R
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : AppCompatActivity(R.layout.activity_edit_profile) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initUI()
        initListeners()
    }

    private fun initUI() {
        val sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE)
        val name = sharedPreferences.getString(SplashActivity.LOGGED_IN_USER_NAME, "No Name")
        val surname = sharedPreferences.getString(SplashActivity.LOGGED_IN_USER_SURNAME, "No Surname")
        val hospital = sharedPreferences.getString(SplashActivity.LOGGED_IN_USER_HOSPITAL, "No Hospital")

        edit_profile_Name_Et.setText(name)
        edit_profile_Surname_Et.setText(surname)
        edit_profile_Hospital_Et.setText(hospital)
    }

    private fun initListeners() {
        edit_profile_Edit_Btn.setOnClickListener {
            val name = edit_profile_Name_Et.text.toString()
            val surname = edit_profile_Surname_Et.text.toString()
            val hospital = edit_profile_Hospital_Et.text.toString()

            if (name.isEmpty() || surname.isEmpty() || hospital.isEmpty()) {
                Toast.makeText(this, R.string.input_text_error, Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            } else {
                val editor = getSharedPreferences(packageName, Context.MODE_PRIVATE).edit()
                editor.putString(SplashActivity.LOGGED_IN_USER_NAME, name)
                editor.putString(SplashActivity.LOGGED_IN_USER_SURNAME, surname)
                editor.putString(SplashActivity.LOGGED_IN_USER_HOSPITAL, hospital)
                editor.commit()
                Toast.makeText(this, R.string.edit_profile_data_saved_msg, Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        edit_profile_Cancel_Btn.setOnClickListener {
            finish()
        }
    }
}
