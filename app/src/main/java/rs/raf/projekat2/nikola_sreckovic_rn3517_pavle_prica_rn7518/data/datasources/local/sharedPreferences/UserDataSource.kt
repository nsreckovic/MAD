package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.datasources.local.sharedPreferences

import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.UserData

interface UserDataSource {
    fun getUserData(): UserData
    fun login(username: String, pin: Int): Boolean
    fun isLoggedIn(): Boolean
}