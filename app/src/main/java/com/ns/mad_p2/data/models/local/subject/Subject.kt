package com.ns.mad_p2.data.models.local.subject

data class Subject (
    val id: Int,
    val subject: String,
    val type: String,
    val professor: String,
    val room: String,
    val groups: String,
    val time: String,
    val day: String
)