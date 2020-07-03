package com.ns.mad_p3.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ns.mad_p3.data.repositories.ModeRepository
import com.ns.mad_p3.presentation.contract.ModeContract

class ModeViewModel(private val modeRepository: ModeRepository) : ViewModel(), ModeContract.ViewModel {

    override fun getMode(): LiveData<Boolean> {
        return modeRepository.getMode()
    }

    override fun getCurrentMode(): Boolean {
        return modeRepository.getCurrentMode()
    }

    override fun flipMode() {
        modeRepository.flipMode()
    }

}