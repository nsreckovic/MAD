package com.ns.mad_p4.presentation.contract

import androidx.lifecycle.LiveData
import com.ns.mad_p4.data.models.ui.WeatherSearchParams
import com.ns.mad_p4.presentation.view.states.MainActivityWeatherState

interface WeatherContract {

    interface ViewModel {
        val mainActivityWeatherState: LiveData<MainActivityWeatherState>

        fun fetchWeatherForCity(city_name: String, days: Int)
        fun getWeatherForCity(params: WeatherSearchParams)
    }

}