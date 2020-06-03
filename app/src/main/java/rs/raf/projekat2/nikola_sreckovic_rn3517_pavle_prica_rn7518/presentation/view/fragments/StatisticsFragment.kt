package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.R
import timber.log.Timber

class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e("statistics crated")
        init()
    }

    private fun init() {
        initListeners()
    }

    private fun initListeners() {

    }

}