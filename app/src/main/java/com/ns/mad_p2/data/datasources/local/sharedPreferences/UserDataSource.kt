package com.ns.mad_p2.data.datasources.local.sharedPreferences

import com.ns.mad_p2.data.models.local.user.UserData

interface UserDataSource {
    fun getUserData(): UserData
    fun login(username: String, pin: Int): Boolean
    fun isLoggedIn(): Boolean
}