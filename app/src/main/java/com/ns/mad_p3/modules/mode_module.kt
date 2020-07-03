package com.ns.mad_p3.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.ns.mad_p3.data.datasources.local.sharedPreferences.ModeDatasource
import com.ns.mad_p3.data.datasources.local.sharedPreferences.ModeDatasourceImpl
import com.ns.mad_p3.data.repositories.ModeRepository
import com.ns.mad_p3.data.repositories.ModeRepositoryImpl
import com.ns.mad_p3.presentation.viewmodel.ModeViewModel

val modeModule = module {

    viewModel { ModeViewModel(modeRepository = get()) }

    single<ModeRepository> { ModeRepositoryImpl(modeDatasource = get()) }

    single<ModeDatasource> { ModeDatasourceImpl(sharedPreferences = get()) }

}