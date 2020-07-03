package rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.view.states

import rs.raf.projekat3.nikola_sreckovic_rn3517.data.models.LocationUI

sealed class ModeState {
    data class Light(val mode: Boolean): ModeState()
    data class Dark(val mode: Boolean): ModeState()
    data class Error(val message: String): ModeState()
}