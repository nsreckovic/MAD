package rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.contract

import androidx.lifecycle.LiveData

interface ModeContract {

    interface ViewModel {
        fun getMode(): LiveData<Boolean>
        fun getCurrentMode(): Boolean
        fun flipMode()
    }

}