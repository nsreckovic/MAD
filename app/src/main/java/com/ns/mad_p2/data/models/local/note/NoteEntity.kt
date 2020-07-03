package com.ns.mad_p2.data.models.local.note

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    var title: String,
    var content: String,
    var archived: String,
    var date: Long
)