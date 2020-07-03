package com.ns.mad_p2.presentation.contract

import androidx.lifecycle.LiveData
import com.ns.mad_p2.data.models.local.subject.Filter
import com.ns.mad_p2.presentation.view.states.TimetableState

interface SubjectContract {

    interface ViewModel {
        val timetableState: LiveData<TimetableState>
        fun fetchAllSubjects()
        fun getAllSubjects()
        fun getAllFilteredSubjects(filter: Filter)
    }

}