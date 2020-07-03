package rs.raf.projekat3.nikola_sreckovic_rn3517.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.datasources.local.database.LocationsDB
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.repositories.LocationRepository
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.repositories.LocationRepositoryImpl
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.viewmodel.LocationsViewModel

val locationModule = module {

    viewModel { LocationsViewModel(locationRepository = get()) }

    single<LocationRepository> { LocationRepositoryImpl(localDataSource = get()) }

    single { get<LocationsDB>().getLocationDao() }

}