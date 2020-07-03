package com.ns.mad_p3.data.datasources.local.sharedPreferences

interface ModeDatasource {
    fun getMode(): Boolean
    fun setMode(mode: Boolean)
    fun flipMode()
}