package com.ns.mad_p4.data.datasources.remote

import com.ns.mad_p4.data.models.remote.WeatherAPIResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast.json")
    fun getByCityName(@Query("q") city_name: String, @Query("days")  days: Int): Observable<WeatherAPIResponse>

}