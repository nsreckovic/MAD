package rs.raf.projekat3.nikola_sreckovic_rn3517.data.datasources.local.sharedPreferences

interface ModeDatasource {
    fun getMode(): Boolean
    fun setMode(mode: Boolean)
    fun flipMode()
}