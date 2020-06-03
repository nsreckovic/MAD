package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.User

interface UserContract {

    interface ViewModel {
        val user: LiveData<User>
        fun getUser()

        fun login(username: String, pin: Int): Boolean
        fun isLoggedIn(): Boolean
    }

}