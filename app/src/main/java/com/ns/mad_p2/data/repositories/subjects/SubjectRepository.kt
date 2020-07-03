package com.ns.mad_p2.data.repositories.subjects

import io.reactivex.Observable
import com.ns.mad_p2.data.models.local.subject.Filter
import com.ns.mad_p2.data.models.local.Resource
import com.ns.mad_p2.data.models.local.subject.Subject

interface SubjectRepository {

    fun fetchAll(): Observable<Resource<Unit>>

    fun getAll(): Observable<Resource<List<Subject>>>

    fun getAllFilteredSubjects(filter: Filter): Observable<Resource<List<Subject>>>

}