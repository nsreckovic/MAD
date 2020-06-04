package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
class SubjectEntity(
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