package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.repositories.subjects

import io.reactivex.Observable
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.subject.Filter
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.Resource
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.subject.Subject

interface SubjectRepository {

    fun fetchAll(): Observable<Resource<Unit>>

    fun getAll(): Observable<Resource<List<Subject>>>

    fun getAllFilteredSubjects(filter: Filter): Observable<Resource<List<Subject>>>

}