package com.ns.mad_p2.data.repositories.users

import com.ns.mad_p2.data.models.local.user.User

interface UserRepository {
    fun getUser(): User
    fun login(username: String, pin: Int): Boolean
    fun isLoggedIn(): Boolean
}