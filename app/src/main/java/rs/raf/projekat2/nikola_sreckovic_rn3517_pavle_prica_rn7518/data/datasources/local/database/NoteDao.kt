package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.datasources.local.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note.NoteEntity

@Dao
abstract class NoteDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: NoteEntity): Completable

    @Update
    abstract fun update(entity: NoteEntity): Completable

    @Query("SELECT * FROM notes")
    abstract fun getAll(): Observable<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE archived = 'false'")
    abstract fun getAllUnarchived(): Observable<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE archived = 'true'")
    abstract fun getAllArchived(): Observable<List<NoteEntity>>

    @Delete
    abstract fun delete(entity: NoteEntity): Completable

}