package com.ns.mad_p3.data.repositories

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Completable
import io.reactivex.Observable
import com.ns.mad_p3.data.datasources.local.database.LocationDao
import com.ns.mad_p3.data.models.LocationEntity
import com.ns.mad_p3.data.models.LocationListFilter
import com.ns.mad_p3.data.models.LocationUI
import com.ns.mad_p3.data.models.Resource
import java.sql.Timestamp

class LocationRepositoryImpl(private val localDataSource: LocationDao) : LocationRepository {

    override fun insert(location: LocationUI): Completable {
        val locationEntity = LocationEntity(location.id, location.title, location.content, location.timestamp.time, location.position.latitude, location.position.longitude)
        return localDataSource.insert(locationEntity)
    }

    override fun update(location: LocationUI): Completable {
        return localDataSource.update(location.id, location.title, location.content, location.position.latitude, location.position.longitude)
    }

    override fun getAll(filter: LocationListFilter): Observable<Resource<List<LocationUI>>> {
        return localDataSource
            .getAll(filter.title, filter.content, filter.order)
            .map {
                val locations = it.map {
                    LocationUI(it.id, it.title, it.content, Timestamp(it.timestamp), LatLng(it.latitude, it.longitude))
                }
                Resource.Success(locations)
            }
    }

    override fun delete(location: LocationUI): Completable {
        return localDataSource.delete(location.id)
    }

}