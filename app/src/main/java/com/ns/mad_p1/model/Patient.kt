package com.ns.mad_p1.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Patient(
    val id: UUID,
    var picture: String,
    var name: String,
    var surname: String,
    var initial_state: String,
    var current_state: String,
    val admission_date: Date,
    var hospitalisation_date: Date?,
    var dismiss_date: Date?
) : Parcelable