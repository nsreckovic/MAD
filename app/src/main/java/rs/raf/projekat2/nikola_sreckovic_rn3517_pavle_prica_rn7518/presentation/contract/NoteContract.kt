package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.contract

import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note.Note

interface NoteContract {

    interface ViewModel {
        fun insert(note: Note)
        fun getAllNote()
        fun getAllArchivedNote()
        fun getAllUnarchivedNote()
    }


}