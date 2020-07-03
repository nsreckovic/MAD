package com.ns.mad_p2.data.repositories.subjects

import io.reactivex.Observable
import com.ns.mad_p2.data.datasources.local.database.SubjectDao
import com.ns.mad_p2.data.datasources.remote.SubjectService
import com.ns.mad_p2.data.models.local.subject.Filter
import com.ns.mad_p2.data.models.local.Resource
import com.ns.mad_p2.data.models.local.subject.Subject
import com.ns.mad_p2.data.models.local.subject.SubjectEntity

class SubjectRepositoryImpl(
    private val localDataSource: SubjectDao,
    private val remoteDataSource: SubjectService
) : SubjectRepository {

    override fun fetchAll(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAll()
            .doOnNext {
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