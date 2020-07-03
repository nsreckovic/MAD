package com.ns.mad_p2.presentation.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.ns.mad_p2.R
import com.ns.mad_p2.presentation.contract.UserContract
import com.ns.mad_p2.presentation.viewmodel.UserViewModel

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    private val userViewModel: UserContract.ViewModel by viewModel<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (userViewModel.isLoggedIn()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        finish()
    }
}
