package com.ns.mad_p4.modules

import com.ns.mad_p4.data.datasources.local.database.WeatherDB
import com.ns.mad_p4.data.datasources.remote.WeatherService
import com.ns.mad_p4.data.repositories.weather.WeatherRepository
import com.ns.mad_p4.data.repositories.weather.WeatherRepositoryImpl
import com.ns.mad_p4.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val weatherModule = module {

    viewModel { WeatherViewModel(weatherRepository = get()) }

    single<WeatherRepository> { WeatherRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<WeatherDB>().getWeatherDao() }

    single<WeatherService> { create(retrofit = get()) }

}