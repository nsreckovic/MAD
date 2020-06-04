package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.states

sealed class NewNoteState {
    object Success: NewNoteState()
    data class Error(val message: String): NewNoteState()
}