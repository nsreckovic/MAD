package com.ns.mad_p2.presentation.view.states

import com.ns.mad_p2.data.models.local.note.ChartData

sealed class StatisticsState {
    data class StatsState(val data: List<ChartData>): StatisticsState()
    data class Error(val message: String): StatisticsState()
}