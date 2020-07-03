package com.ns.mad_p3.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.ns.mad_p3.data.datasources.local.database.LocationsDB
import com.ns.mad_p3.data.repositories.LocationRepository
import com.ns.mad_p3.data.repositories.LocationRepositoryImpl
import com.ns.mad_p3.presentation.viewmodel.LocationsViewModel

val locationModule = module {

    viewModel { LocationsViewModel(locationRepository = get()) }

    single<LocationRepository> { LocationRepositoryImpl(localDataSource = get()) }

    single { get<LocationsDB>().getLocationDao() }

}