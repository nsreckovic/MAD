package com.ns.mad_p2.data.datasources.local.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import com.ns.mad_p2.data.models.local.subject.SubjectEntity

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

    @Query("SELECT * FROM subjects WHERE (groups LIKE :group) AND (day LIKE :day) AND ((subject LIKE :professor_subject) OR (professor LIKE :professor_subject))")
    abstract fun getFiltered(group: String, day: String, professor_subject: String) : Observable<List<SubjectEntity>>
}