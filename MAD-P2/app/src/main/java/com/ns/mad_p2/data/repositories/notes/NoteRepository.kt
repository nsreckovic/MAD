package com.ns.mad_p2.data.repositories.notes

import io.reactivex.Completable
import io.reactivex.Observable
import com.ns.mad_p2.data.models.local.Resource
import com.ns.mad_p2.data.models.local.note.ChartData
import com.ns.mad_p2.data.models.local.note.Note
import com.ns.mad_p2.data.models.local.note.NoteFilter

interface NoteRepository {

    fun insert(note: Note): Completable

    fun update(note: Note): Completable

    fun getAll(): Observable<Resource<List<Note>>>

    fun getAllArchived(): Observable<Resource<List<Note>>>

    fun getAllUnarchived(): Observable<Resource<List<Note>>>

    fun getFilteredNotes(filter: NoteFilter): Observable<Resource<List<Note>>>

    fun delete(note: Note): Completable

    fun getNotesFromLast5Days(): Observable<Resource<List<ChartData>>>

}