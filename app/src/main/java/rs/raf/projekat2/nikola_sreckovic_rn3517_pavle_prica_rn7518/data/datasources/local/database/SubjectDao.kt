package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.datasources.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.SubjectEntity

@Dao
abstract class SubjectDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: SubjectEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<SubjectEntity>): Completable

    @Query("SELECT * FROM subjects")
    abstract fun getAll(): Observable<List<SubjectEntity>>

    @Query("DELETE FROM subjects")
    abstract fun deleteAll()

}