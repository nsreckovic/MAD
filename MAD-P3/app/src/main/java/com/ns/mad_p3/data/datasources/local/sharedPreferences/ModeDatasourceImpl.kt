package com.ns.mad_p3.data.datasources.local.sharedPreferences

import android.content.SharedPreferences

class ModeDatasourceImpl(private val sharedPreferences: SharedPreferences) : ModeDatasource{

    companion object {
        const val MODE_KEY = "mode_key"
    }

    init {
        val editor = sharedPreferences.edit()
        editor.putBoolean(MODE_KEY, sharedPreferences.getBoolean(MODE_KEY, false))
        editor.apply()
    }

    override fun getMode(): Boolean {
        return sharedPreferences.getBoolean(MODE_KEY, false)
    }

    override fun setMode(mode: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(MODE_KEY, mode)
        editor.apply()
    }

    override fun flipMode() {
        val editor = sharedPreferences.edit()
        editor.putBoolean(MODE_KEY, !sharedPreferences.getBoolean(MODE_KEY, false))
        editor.apply()
    }

}