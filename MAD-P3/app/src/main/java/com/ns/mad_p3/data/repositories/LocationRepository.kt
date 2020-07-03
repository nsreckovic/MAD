package com.ns.mad_p3.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import com.ns.mad_p3.data.models.LocationListFilter
import com.ns.mad_p3.data.models.LocationUI
import com.ns.mad_p3.data.models.Resource

interface LocationRepository {

    fun insert(location: LocationUI): Completable

    fun update(location: LocationUI): Completable

    fun getAll(filter: LocationListFilter): Observable<Resource<List<LocationUI>>>

    fun delete(location: LocationUI): Completable

}