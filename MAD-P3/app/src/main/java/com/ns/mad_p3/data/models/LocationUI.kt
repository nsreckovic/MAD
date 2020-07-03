package com.ns.mad_p3.data.models

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize
import java.sql.Timestamp

@Parcelize
data class LocationUI(
    val id: Int,
    var title: String,
    var content: String,
    val timestamp: Timestamp,
    val position: LatLng
) : Parcelable {
}