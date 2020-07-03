package com.ns.mad_p2.presentation.contract

import androidx.lifecycle.LiveData
import com.ns.mad_p2.data.models.local.note.Note
import com.ns.mad_p2.data.models.local.note.NoteFilter
import com.ns.mad_p2.presentation.view.states.NewNoteState
import com.ns.mad_p2.presentation.view.states.NotesState
import com.ns.mad_p2.presentation.view.states.StatisticsState

interface NoteContract {

    interface ViewModel {
        val notesState: LiveData<NotesState>
        val statsState: LiveData<StatisticsState>
        val addNoteDone: LiveData<NewNoteState>

        fun insert(note: Note)
        fun archive(note: Note)
        fun update(note: Note)
        fun delete(note: Note)
        fun getAllNotes()
        fun getAllArchivedNotes()
        fun getAllUnarchivedNotes()
        fun getFilteredNotes(filter: NoteFilter)
        fun getNotesFromLast5Days()
    }


}