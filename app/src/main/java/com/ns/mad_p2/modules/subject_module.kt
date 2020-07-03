package com.ns.mad_p2.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.ns.mad_p2.data.datasources.local.database.StudentHelperDB
import com.ns.mad_p2.data.datasources.remote.SubjectService
import com.ns.mad_p2.data.repositories.subjects.SubjectRepository
import com.ns.mad_p2.data.repositories.subjects.SubjectRepositoryImpl
import com.ns.mad_p2.presentation.viewmodel.SubjectViewModel

val subjectModule = module {

    viewModel { SubjectViewModel(subjectRepository = get()) }

    single<SubjectRepository> { SubjectRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<StudentHelperDB>().getSubjectDao() }

    single<SubjectService> { create(retrofit = get()) }

}