package com.ns.mad_p2.data.datasources.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ns.mad_p2.data.models.local.note.NoteEntity
import com.ns.mad_p2.data.models.local.subject.SubjectEntity

@Database(
    entities = [SubjectEntity::class, NoteEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters()
abstract class StudentHelperDB : RoomDatabase() {
    abstract fun getSubjectDao(): SubjectDao
    abstract fun getNoteDao(): NoteDao
}