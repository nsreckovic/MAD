package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.repositories.subjects

import io.reactivex.Observable
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.Subject

interface SubjectRepository {

    fun fetchAll(): Observable<List<Subject>>

}