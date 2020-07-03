package com.ns.mad_p2.data.models.local.user

data class UserData(
    val username: String,
    val pin: Int
)

fun UserData.toUser(): User {
    return User(
        username
    )
}