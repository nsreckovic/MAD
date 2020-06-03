package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local

data class UserData(
    val username: String,
    val pin: Int
)

fun UserData.toUser(): User {
    return User(username)
}