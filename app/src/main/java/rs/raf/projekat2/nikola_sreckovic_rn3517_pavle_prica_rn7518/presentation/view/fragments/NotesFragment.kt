package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_notes.*
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.R
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.activities.NewNoteActivity
import timber.log.Timber

class NotesFragment : Fragment(R.layout.fragment_notes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e("notes crated")
        init()
    }

    private fun init() {
        initListeners()
    }

    private fun initListeners() {
        notes_NewNote_Btn.setOnClickListener {
            val intent = Intent(context, NewNoteActivity::class.java)
            startActivity(intent)
        }
    }

}