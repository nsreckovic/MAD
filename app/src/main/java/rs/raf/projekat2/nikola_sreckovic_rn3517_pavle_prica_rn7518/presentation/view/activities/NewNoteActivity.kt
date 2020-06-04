package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new_note.*
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.R

class NewNoteActivity : AppCompatActivity(R.layout.activity_new_note) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        new_note_Cancel_Btn.setOnClickListener {
            finish()
        }
    }

}
