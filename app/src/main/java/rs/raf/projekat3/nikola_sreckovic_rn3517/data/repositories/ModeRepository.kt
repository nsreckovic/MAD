package rs.raf.projekat3.nikola_sreckovic_rn3517.data.repositories

import androidx.lifecycle.LiveData

interface ModeRepository {
    val darkmode: LiveData<Boolean>

    fun getMode(): LiveData<Boolean>
    fun getCurrentMode(): Boolean
    fun flipMode()
}