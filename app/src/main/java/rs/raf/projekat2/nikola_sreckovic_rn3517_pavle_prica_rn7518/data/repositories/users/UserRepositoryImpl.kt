package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.repositories.users


import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.datasources.local.sharedPreferences.UserDataSource
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.user.User
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.user.toUser

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