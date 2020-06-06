package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_new_note.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.R
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note.Note
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.contract.NoteContract
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.states.NewNoteState
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.viewmodel.NoteViewModel

class NewNoteActivity : AppCompatActivity(R.layout.activity_new_note) {

    private val noteViewModel: NoteContract.ViewModel by viewModel<NoteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        new_note_Cancel_Btn.setOnClickListener {
            finish()
        }

        new_note_Save_Btn.setOnClickListener {
            val title = new_note_Title_Et.text.toString()
            val content = new_note_Content_Et.text.toString()
            if (!title.isBlank()) {
                if (!content.isBlank()) {
                    noteViewModel.insert(Note(0, title, content, "false"))
                    finish()
                } else Toast.makeText(this, R.string.new_note_content_error, Toast.LENGTH_SHORT).show()
            } else Toast.makeText(this, R.string.new_note_title_error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initObservers() {
        noteViewModel.addNoteDone.observe(this, Observer {
            renderState(it)
        })
    }

    private fun renderState(state: NewNoteState) {
        when(state) {
            is NewNoteState.Success -> Toast.makeText(this, R.string.new_note_success, Toast.LENGTH_SHORT).show()
            is NewNoteState.Error -> Toast.makeText(this, R.string.new_note_error, Toast.LENGTH_SHORT).show()
        }
    }

}
