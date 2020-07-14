package com.ns.mad_p4.data.repositories.weather

import com.ns.mad_p4.data.models.local.Resource
import com.ns.mad_p4.data.models.ui.WeatherUI
import io.reactivex.Observable

interface WeatherRepository {

    fun fetchByCityName(city_name: String, days: Int): Observable<Resource<Unit>>

    fun getByCityName(city_name: String, date: Long, days: Int): Observable<Resource<List<WeatherUI>>>

}