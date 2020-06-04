package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.viewmodel

import androidx.lifecycle.ViewModel
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note.Note
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.repositories.notes.NoteRepository
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.contract.NoteContract

class NoteViewModel (
    private val noteRepository: NoteRepository
) : ViewModel(), NoteContract.ViewModel  {

    override fun insert(note: Note) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllNote() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllArchivedNote() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllUnarchivedNote() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}