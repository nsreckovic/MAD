package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.user.User
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.repositories.users.UserRepository
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.contract.UserContract

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