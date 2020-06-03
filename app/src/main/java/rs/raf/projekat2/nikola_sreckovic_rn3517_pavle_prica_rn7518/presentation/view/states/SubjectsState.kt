package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.states

import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.Subject

sealed class SubjectsState {
    object Loading: SubjectsState()
    object DataFetched: SubjectsState()
    data class Success(val movies: List<Subject>): SubjectsState()
    data class Error(val message: String): SubjectsState()
}