package com.ns.mad_p2.data.models.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SubjectResponse (
    @Json(name = "predmet") val subject: String,
    @Json(name = "tip") val type: String,
    @Json(name = "nastavnik") val professor: String,
    @Json(name = "ucionica") val room: String,
    @Json(name = "grupe") val groups: String,
    @Json(name = "termin") val time: String,
    @Json(name = "dan") val day: String
)