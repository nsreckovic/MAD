package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.states

import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note.Note

sealed class NotesState {
    object DataFetched: NotesState()
    data class Success(val notes: List<Note>): NotesState()
    data class Error(val message: String): NotesState()
}