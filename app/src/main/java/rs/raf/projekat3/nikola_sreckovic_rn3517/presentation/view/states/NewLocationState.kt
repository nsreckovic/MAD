package rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.view.states

sealed class NewLocationState {
    data class Success(val message: String): NewLocationState()
    data class Error(val message: String): NewLocationState()
}