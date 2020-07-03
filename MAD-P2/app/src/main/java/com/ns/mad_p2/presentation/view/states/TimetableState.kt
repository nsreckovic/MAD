package com.ns.mad_p2.presentation.view.states

import com.ns.mad_p2.data.models.local.subject.Subject

sealed class TimetableState {
    object Loading: TimetableState()
    object DataFetched: TimetableState()
    data class Success(val movies: List<Subject>): TimetableState()
    data class Error(val message: String): TimetableState()
}