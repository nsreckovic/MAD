package com.ns.mad_p4.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
class WeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Int
) {
}