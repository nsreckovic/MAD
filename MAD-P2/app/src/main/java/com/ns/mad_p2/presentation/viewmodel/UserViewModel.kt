package com.ns.mad_p2.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ns.mad_p2.data.models.local.user.User
import com.ns.mad_p2.data.repositories.users.UserRepository
import com.ns.mad_p2.presentation.contract.UserContract

class UserViewModel(private val userRepository: UserRepository) : ViewModel(), UserContract.ViewModel {

    override val user: MutableLiveData<User> = MutableLiveData()

    override fun getUser() {
        user.value = userRepository.getUser()
    }

    override fun login(username: String, pin: Int): Boolean {
        return userRepository.login(username, pin)
    }

    override fun isLoggedIn(): Boolean {
        return userRepository.isLoggedIn()
    }

}