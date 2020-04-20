package com.ns.mad_p1.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ns.mad_p1.R
import com.ns.mad_p1.view.activities.EditProfileActivity
import com.ns.mad_p1.view.activities.SplashActivity
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.fragment_profile.*
import timber.log.Timber

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onResume() {
        super.onResume()
        initUI()
    }

    private fun init() {
        initUI()
        initListeners()
    }

    private fun initUI() {
        val sharedPreferences = context?.getSharedPreferences(context?.packageName, Context.MODE_PRIVATE)
        val name = sharedPreferences?.getString(SplashActivity.LOGGED_IN_USER_NAME, "No Name")
        val surname = sharedPreferences?.getString(SplashActivity.LOGGED_IN_USER_SURNAME, "No Surname")
        val hospital = sharedPreferences?.getString(SplashActivity.LOGGED_IN_USER_HOSPITAL, "No Hospital")

        profile_WorkerName_Tv.setText(name)
        profile_WorkerSurname_Tv.setText(surname)
        profile_WorkerHospital_Tv.setText(hospital)
    }

    private fun initListeners() {
        profile_Edit_Btn.setOnClickListener {
            val intent = Intent(context, EditProfileActivity::class.java)
            startActivity(intent)
        }

        profile_SignOut_Btn.setOnClickListener {
            val editor = context?.getSharedPreferences(context?.packageName, Context.MODE_PRIVATE)?.edit()
            if (editor != null) {
                editor.putBoolean(SplashActivity.LOGGED_IN, false)
                editor.commit()
                val intent = Intent(context, SplashActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }
    }

}