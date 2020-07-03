package com.ns.mad_p2.data.models.local.note

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Note (
    val id: Int,
    var title: String,
    var content: String,
    var archived: String
) : Parcelable