package rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.view.states

import rs.raf.projekat3.nikola_sreckovic_rn3517.data.models.LocationUI

sealed class LocationsState {
    data class Success(val locations: List<LocationUI>): LocationsState()
    data class Error(val message: String): LocationsState()
    data class OperationSuccess(val message: String): LocationsState()
}