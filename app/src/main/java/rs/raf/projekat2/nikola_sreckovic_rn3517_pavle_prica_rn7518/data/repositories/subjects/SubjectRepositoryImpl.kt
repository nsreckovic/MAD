package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.repositories.subjects

import io.reactivex.Observable
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.datasources.local.database.SubjectDao
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.datasources.remote.SubjectService
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.subject.Filter
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.Resource
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.subject.Subject
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.subject.SubjectEntity
import timber.log.Timber

class SubjectRepositoryImpl(
    private val localDataSource: SubjectDao,
    private val remoteDataSource: SubjectService
) : SubjectRepository {

    override fun fetchAll(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAll()
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.map {
                    SubjectEntity(
                        0,
                        it.subject,
                        it.type,
                        it.professor,
                        it.room,
                        it.groups,
                        it.time,
                        it.day
                    )
                }
                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getAll(): Observable<Resource<List<Subject>>> {
        return localDataSource
            .getAll()
            .doOnNext {
                Timber.e("Citanje iz baze")
            }
            .map {
                val subjects = it.map {
                    Subject(
                        it.id,
                        it.subject,
                        it.type,
                        it.professor,
                        it.room,
                        it.groups,
                        it.time,
                        it.day
                    )
                }
                Resource.Success(subjects)
            }
    }

    override fun getAllFilteredSubjects(filter: Filter): Observable<Resource<List<Subject>>> {
        return localDataSource
            .getFiltered(filter.group, filter.day, filter.professor_subject)
            .doOnNext {
                Timber.e("Citanje iz baze")
            }
            .map {
                val subjects = it.map {
                    Subject(
                        it.id,
                        it.subject,
                        it.type,
                        it.professor,
                        it.room,
                        it.groups,
                        it.time,
                        it.day
                    )
                }
                Resource.Success(subjects)
            }
    }

}