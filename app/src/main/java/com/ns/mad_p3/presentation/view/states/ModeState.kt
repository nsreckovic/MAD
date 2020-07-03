package com.ns.mad_p3.presentation.view.states

sealed class ModeState {
    data class Light(val mode: Boolean): ModeState()
    data class Dark(val mode: Boolean): ModeState()
    data class Error(val message: String): ModeState()
}