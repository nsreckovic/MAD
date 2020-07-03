package com.ns.mad_p3.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class LocationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val content: String,
    val timestamp: Long,
    val latitude: Double,
    val longitude: Double
) {

}