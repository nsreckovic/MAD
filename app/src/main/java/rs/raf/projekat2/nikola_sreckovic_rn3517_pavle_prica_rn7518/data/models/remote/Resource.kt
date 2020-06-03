package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.remote

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Loading<out T>(val message: String = "") : Resource<T>()
    data class Error<out T>(val error: Throwable = Throwable(), val data: T? = null): Resource<T>()
}