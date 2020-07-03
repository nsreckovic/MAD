package com.ns.mad_p2.data.repositories.users


import com.ns.mad_p2.data.datasources.local.sharedPreferences.UserDataSource
import com.ns.mad_p2.data.models.local.user.User
import com.ns.mad_p2.data.models.local.user.toUser

class UserRepositoryImpl(private val userDataSource: UserDataSource) : UserRepository {

    override fun getUser(): User {
        return userDataSource.getUserData().toUser()
    }

    override fun login(username: String, pin: Int): Boolean {
        return userDataSource.login(username, pin)
    }

    override fun isLoggedIn(): Boolean {
        return userDataSource.isLoggedIn()
    }


}