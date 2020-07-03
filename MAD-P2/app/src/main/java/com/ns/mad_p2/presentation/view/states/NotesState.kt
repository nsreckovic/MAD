package com.ns.mad_p2.presentation.view.states

import com.ns.mad_p2.data.models.local.note.ChartData
import com.ns.mad_p2.data.models.local.note.Note

sealed class NotesState {
    object DataFetched: NotesState()
    data class Success(val notes: List<Note>): NotesState()
    data class Error(val message: String): NotesState()
    data class OperationSuccess(val message: String): NotesState()
    data class StatisticsState(val data: List<ChartData>): NotesState()
}