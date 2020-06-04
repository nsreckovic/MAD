package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note.Note
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.states.NewNoteState
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.states.NotesState

interface NoteContract {

    interface ViewModel {
        val notesState: LiveData<NotesState>
        val addNoteDone: LiveData<NewNoteState>

        fun insert(note: Note)
        fun getAllNotes()
        fun getAllArchivedNotes()
        fun getAllUnarchivedNotes()
    }


}