package com.ns.mad_p2.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import com.ns.mad_p2.data.models.remote.SubjectResponse

interface SubjectService {

    @GET("raspored/json.php")
    fun getAll(): Observable<List<SubjectResponse>>

}