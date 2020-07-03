package rs.raf.projekat3.nikola_sreckovic_rn3517.data.models

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