package com.ns.mad_p2.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.ns.mad_p2.data.datasources.local.database.StudentHelperDB
import com.ns.mad_p2.data.repositories.notes.NoteRepository
import com.ns.mad_p2.data.repositories.notes.NoteRepositoryImpl
import com.ns.mad_p2.presentation.viewmodel.NoteViewModel

val noteModule = module {

    viewModel { NoteViewModel(noteRepository = get()) }

    single<NoteRepository> { NoteRepositoryImpl(localDataSource = get()) }

    single { get<StudentHelperDB>().getNoteDao() }

}