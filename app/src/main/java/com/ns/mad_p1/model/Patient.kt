package com.ns.mad_p1.model

import java.util.*

data class Patient(
    val id: UUID,
    val picture: String,
    val name: String,
    val surname: String,
    val initial_state: String,
    val current_state: String,
    val admission_date: Date,
    var hospitalisation_date: Date?,
    var dismiss_date: Date?
)