package com.ns.mad_p4.data.models

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize
import java.sql.Timestamp

@Parcelize
class WeatherUI(
    val id: Int,
    val icon_link: String,
    val city_name: String,
    val temp_max: String,
    val temp_min: String,
    val temp_avg: String,
    val wind_speed: String,
    val uv_coef: String,
    val date: Timestamp,
    val position: LatLng
) : Parcelable {
}