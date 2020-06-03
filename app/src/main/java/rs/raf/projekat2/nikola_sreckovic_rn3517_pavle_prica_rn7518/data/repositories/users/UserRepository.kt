package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.repositories.users

import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.User

interface UserRepository {
    fun getUser(): User
    fun login(username: String, pin: Int): Boolean
    fun isLoggedIn(): Boolean
}