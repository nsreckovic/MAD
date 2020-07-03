package com.ns.mad_p2.data.datasources.local.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import com.ns.mad_p2.data.models.local.note.NoteEntity

@Dao
abstract class NoteDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: NoteEntity): Completable

    @Query("UPDATE notes SET title = :title, content = :content, archived = :archived WHERE id = :id")
    abstract fun update(id: Int, title: String, content: String, archived: String): Completable

    @Query("SELECT * FROM notes")
    abstract fun getAll(): Observable<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id = :id")
    abstract fun getById(id: Int): NoteEntity

    @Query("SELECT * FROM notes WHERE archived = 'false'")
    abstract fun getAllUnarchived(): Observable<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE archived = 'true'")
    abstract fun getAllArchived(): Observable<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE ((title LIKE :title_content) OR (content LIKE :title_content)) AND (archived LIKE :archived)")
    abstract fun getFiltered(title_content: String, archived: String): Observable<List<NoteEntity>>

    @Query("DELETE FROM notes WHERE id = :id")
    abstract fun delete(id: Int): Completable

    @Query("SELECT * FROM notes WHERE date >= :period")
    abstract fun getAllFromLast5Days(period: Long): Observable<List<NoteEntity>>

}