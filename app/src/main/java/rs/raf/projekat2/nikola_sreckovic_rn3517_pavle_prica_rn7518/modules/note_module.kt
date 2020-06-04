package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.datasources.local.database.StudentHelperDB
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.repositories.notes.NoteRepository
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.repositories.notes.NoteRepositoryImpl
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.viewmodel.NoteViewModel

val noteModule = module {

    viewModel { NoteViewModel(noteRepository = get()) }

    single<NoteRepository> { NoteRepositoryImpl(localDataSource = get()) }

    single { get<StudentHelperDB>().getNoteDao() }

}