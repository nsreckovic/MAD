package com.ns.mad_p4.data.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
class CityEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val timezone_id: String,
    val local_time: String
) {
}