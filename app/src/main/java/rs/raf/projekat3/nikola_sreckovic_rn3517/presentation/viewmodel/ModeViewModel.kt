package rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.repositories.ModeRepository
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.contract.ModeContract

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