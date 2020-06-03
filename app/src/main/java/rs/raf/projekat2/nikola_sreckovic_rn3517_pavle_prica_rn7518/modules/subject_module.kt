package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.datasources.local.database.StudentHelperDB
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.datasources.remote.SubjectService
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.repositories.subjects.SubjectRepository
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.repositories.subjects.SubjectRepositoryImpl
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.viewmodel.SubjectViewModel

val subjectModule = module {

    viewModel { SubjectViewModel(subjectRepository = get()) }

    single<SubjectRepository> { SubjectRepositoryImpl(remoteDataSource = get()) }

    single { get<StudentHelperDB>().getSubjectDao() }

    single<SubjectService> { create(retrofit = get()) }

}