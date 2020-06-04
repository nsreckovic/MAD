package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.datasources.local.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.Filter
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.SubjectEntity

@Dao
abstract class SubjectDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<SubjectEntity>): Completable

    @Query("SELECT * FROM subjects")
    abstract fun getAll(): Observable<List<SubjectEntity>>

    @Query("DELETE FROM subjects")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<SubjectEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }
    // AND (day LIKE '' || :day) AND ((subject LIKE '' || '%' + :professor_subject + '%') OR (professor LIKE '' || '%' + :professor_subject + '%'))
    // , day: String, professor_subject: String
    @Query("SELECT * FROM subjects WHERE (groups LIKE :group) AND (day LIKE :day) AND ((subject LIKE :professor_subject) OR (professor LIKE :professor_subject))")
    abstract fun getFiltered(group: String, day: String, professor_subject: String) : Observable<List<SubjectEntity>>
}