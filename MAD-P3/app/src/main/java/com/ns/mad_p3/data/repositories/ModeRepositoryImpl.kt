package com.ns.mad_p3.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ns.mad_p3.data.datasources.local.sharedPreferences.ModeDatasource

class ModeRepositoryImpl(private val modeDatasource: ModeDatasource) : ModeRepository {

    override val darkmode: MutableLiveData<Boolean> = MutableLiveData()

    override fun getMode(): LiveData<Boolean> {
        return darkmode
    }

    override fun getCurrentMode(): Boolean {
        return modeDatasource.getMode()
    }

    override fun flipMode() {
        modeDatasource.flipMode()
        darkmode.value = modeDatasource.getMode()
    }
}