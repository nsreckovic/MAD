package com.ns.mad_p3.presentation.contract

import androidx.lifecycle.LiveData
import com.ns.mad_p3.data.models.LocationListFilter
import com.ns.mad_p3.data.models.LocationUI
import com.ns.mad_p3.presentation.view.states.LocationsState
import com.ns.mad_p3.presentation.view.states.NewLocationState

interface LocationContract {

    interface ViewModel {
        val locationsState: LiveData<LocationsState>
        val addLocationDone: LiveData<NewLocationState>

        fun insert(location: LocationUI)
        fun update(location: LocationUI)
        fun delete(location: LocationUI)
        fun getAll(filter: LocationListFilter)
    }

}