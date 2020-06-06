package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.states

import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note.ChartData

sealed class StatisticsState {
    data class StatsState(val data: List<ChartData>): StatisticsState()
    data class Error(val message: String): StatisticsState()
}