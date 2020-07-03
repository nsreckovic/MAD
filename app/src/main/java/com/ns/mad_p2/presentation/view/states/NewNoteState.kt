package com.ns.mad_p2.presentation.view.states

sealed class NewNoteState {
    object Success: NewNoteState()
    data class Error(val message: String): NewNoteState()
}