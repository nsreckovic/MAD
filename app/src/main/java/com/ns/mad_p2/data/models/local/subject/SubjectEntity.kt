package com.ns.mad_p2.data.models.local.subject

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class SubjectEntity(
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val subject: String,
    val type: String,
    val professor: String,
    val room: String,
    val groups: String,
    val time: String,
    val day: String
)