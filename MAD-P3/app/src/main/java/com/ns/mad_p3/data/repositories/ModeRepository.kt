package com.ns.mad_p3.data.repositories

import androidx.lifecycle.LiveData

interface ModeRepository {
    val darkmode: LiveData<Boolean>

    fun getMode(): LiveData<Boolean>
    fun getCurrentMode(): Boolean
    fun flipMode()
}