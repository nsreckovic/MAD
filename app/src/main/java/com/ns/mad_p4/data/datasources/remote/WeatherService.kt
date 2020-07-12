package com.ns.mad_p4.data.datasources.remote

import com.ns.mad_p4.data.models.remote.WeatherAPIResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("forecast.json?key=6d590680660b4c44ad5121505201107&q={city_name}&days={days}")
    fun getByCityName(@Path("city_name") city_name: String, @Path("days") days: Int): Observable<WeatherAPIResponse>

}