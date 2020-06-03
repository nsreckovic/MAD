package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.Subject

interface SubjectContract {

    interface ViewModel {
        val subjects: LiveData<List<Subject>>

        fun fetchAllSubjects()
    }

}