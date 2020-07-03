package rs.raf.projekat3.nikola_sreckovic_rn3517.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.datasources.local.sharedPreferences.ModeDatasource
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.datasources.local.sharedPreferences.ModeDatasourceImpl
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.repositories.ModeRepository
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.repositories.ModeRepositoryImpl
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.viewmodel.ModeViewModel

val modeModule = module {

    viewModel { ModeViewModel(modeRepository = get()) }

    single<ModeRepository> { ModeRepositoryImpl(modeDatasource = get()) }

    single<ModeDatasource> { ModeDatasourceImpl(sharedPreferences = get()) }

}