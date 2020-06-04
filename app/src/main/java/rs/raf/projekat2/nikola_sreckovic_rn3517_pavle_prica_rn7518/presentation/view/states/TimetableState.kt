package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.states

import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.subject.Subject

sealed class TimetableState {
    object Loading: TimetableState()
    object DataFetched: TimetableState()
    data class Success(val movies: List<Subject>): TimetableState()
    data class Error(val message: String): TimetableState()
}