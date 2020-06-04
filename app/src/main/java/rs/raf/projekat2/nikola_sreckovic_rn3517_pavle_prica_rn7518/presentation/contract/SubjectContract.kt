package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.Filter
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.Subject
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.states.TimetableState

interface SubjectContract {

    interface ViewModel {
        val timetableState: LiveData<TimetableState>
        fun fetchAllSubjects()
        fun getAllSubjects()
        fun getAllFilteredSubjects(filter: Filter)
    }

}