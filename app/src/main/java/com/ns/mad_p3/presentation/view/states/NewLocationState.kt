package com.ns.mad_p3.presentation.view.states

sealed class NewLocationState {
    data class Success(val message: String): NewLocationState()
    data class Error(val message: String): NewLocationState()
}