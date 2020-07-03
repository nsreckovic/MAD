package com.ns.mad_p3.presentation.contract

import androidx.lifecycle.LiveData

interface ModeContract {

    interface ViewModel {
        fun getMode(): LiveData<Boolean>
        fun getCurrentMode(): Boolean
        fun flipMode()
    }

}