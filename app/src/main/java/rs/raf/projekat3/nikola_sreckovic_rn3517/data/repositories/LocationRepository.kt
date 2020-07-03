package rs.raf.projekat3.nikola_sreckovic_rn3517.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.models.LocationListFilter
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.models.LocationUI
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.models.Resource

interface LocationRepository {

    fun insert(location: LocationUI): Completable

    fun update(location: LocationUI): Completable

    fun getAll(filter: LocationListFilter): Observable<Resource<List<LocationUI>>>

    fun delete(location: LocationUI): Completable

}