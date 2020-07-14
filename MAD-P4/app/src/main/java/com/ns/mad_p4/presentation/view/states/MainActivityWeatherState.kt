package com.ns.mad_p4.presentation.view.states

import com.ns.mad_p4.data.models.ui.WeatherUI

sealed class MainActivityWeatherState {
    object Loading: MainActivityWeatherState()
    object DataFetched: MainActivityWeatherState()
    data class Success(val weather: List<WeatherUI>): MainActivityWeatherState()
    data class Error(val message: String): MainActivityWeatherState()
}