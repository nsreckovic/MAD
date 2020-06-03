package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.repositories.subjects

import io.reactivex.Observable
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.datasources.remote.SubjectService
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.Subject
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.remote.Resource
import timber.log.Timber

class SubjectRepositoryImpl(
    private val remoteDataSource: SubjectService
) : SubjectRepository {

    override fun fetchAll(): Observable<List<Subject>> {
        return remoteDataSource
            .getAll()
            .map {
                it.map {
                    Subject(
                        it.subject,
                        it.type,
                        it.professor,
                        it.room,
                        it.groups,
                        it.time,
                        it.day
                    )
                }
            }
    }

}