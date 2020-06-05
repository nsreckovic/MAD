package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.repositories.notes

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.Resource
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note.Note
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note.NoteFilter

interface NoteRepository {

    fun insert(note: Note): Completable

    fun update(note: Note): Completable

    fun getAll(): Observable<Resource<List<Note>>>

    fun getAllArchived(): Observable<Resource<List<Note>>>

    fun getAllUnarchived(): Observable<Resource<List<Note>>>

    fun getFilteredNotes(filter: NoteFilter): Observable<Resource<List<Note>>>

    fun delete(note: Note): Completable

}