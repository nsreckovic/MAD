package com.ns.mad_p3.presentation.view.states

import com.ns.mad_p3.data.models.LocationUI

sealed class LocationsState {
    data class Success(val locations: List<LocationUI>): LocationsState()
    data class Error(val message: String): LocationsState()
    data class OperationSuccess(val message: String): LocationsState()
}