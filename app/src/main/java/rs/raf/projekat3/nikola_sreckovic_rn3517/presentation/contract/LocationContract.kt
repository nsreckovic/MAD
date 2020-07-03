package rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.contract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.models.LocationListFilter
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.models.LocationUI
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.view.states.LocationsState
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.view.states.NewLocationState

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