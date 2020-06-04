package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.remote.SubjectResponse

interface SubjectService {

    @GET("raspored/json.php")
    fun getAll(): Observable<List<SubjectResponse>>

}