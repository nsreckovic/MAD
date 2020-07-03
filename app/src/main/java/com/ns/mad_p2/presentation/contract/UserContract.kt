package com.ns.mad_p2.presentation.contract

import androidx.lifecycle.LiveData
import com.ns.mad_p2.data.models.local.user.User

interface UserContract {

    interface ViewModel {
        val user: LiveData<User>
        fun getUser()

        fun login(username: String, pin: Int): Boolean
        fun isLoggedIn(): Boolean
    }

}