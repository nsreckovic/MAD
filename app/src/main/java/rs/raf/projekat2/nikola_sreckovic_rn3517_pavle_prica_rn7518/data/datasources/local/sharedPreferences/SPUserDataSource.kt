package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.datasources.local.sharedPreferences

import android.content.SharedPreferences
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.UserData

class SPUserDataSource(
    private val sharedPreferences: SharedPreferences
) : UserDataSource {

    companion object {
        const val LOGGED_IN_KEY = "UserLoggedIN"
        const val USER_USERNAME_KEY = "UserUsernameKey"
        const val USER_PIN_KEY = "UserPinKey"
    }

    init {
        val editor = sharedPreferences.edit()
        editor.putString(USER_USERNAME_KEY, "test")
        editor.putInt(USER_PIN_KEY, 1234)
        editor.apply()
    }

    override fun getUserData(): UserData {
        val username = sharedPreferences.getString(USER_USERNAME_KEY, "") ?: ""
        val pin = sharedPreferences.getInt(USER_PIN_KEY, 0)
        return UserData(username, pin)
    }

    override fun login(username: String, pin: Int): Boolean {
        val spUsername = sharedPreferences.getString(USER_USERNAME_KEY, "") ?: ""
        val spPin = sharedPreferences.getInt(USER_PIN_KEY, 0)
        if (username == spUsername && pin == spPin) {
            val editor = sharedPreferences.edit()
            editor.putBoolean(LOGGED_IN_KEY, true)
            editor.apply()
            return true
        };
        return false;
    }

    override fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(LOGGED_IN_KEY, false)
    }

}