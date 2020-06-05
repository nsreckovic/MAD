package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_note.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.R
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note.Note
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.contract.NoteContract
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.viewmodel.NoteViewModel
import timber.log.Timber

class EditNoteActivity : AppCompatActivity(R.layout.activity_edit_note) {

    companion object {
        const val NOTE_KEY = "NoteKey"
    }

    private lateinit var note: Note
    private val noteViewModel: NoteContract.ViewModel by viewModel<NoteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        parseIntent()
        initUi()
    }

    private fun initUi() {
        initListeners()
        initData()
    }

    private fun initData() {
        edit_note_Title_Et.setText(note.title)
        edit_note_Content_Et.setText(note.content)
    }

    private fun initListeners() {
        edit_note_Cancel_Btn.setOnClickListener {
            finish()
        }
        edit_note_Save_Btn.setOnClickListener {
            val title = edit_note_Title_Et.text.toString()
            val content = edit_note_Content_Et.text.toString()
            if (title != note.title || content != note.content) {
                note.title = title
                note.content = content
                Timber.e(note.toString())
                noteViewModel.update(note)
            }
            finish()
        }
    }

    private fun parseIntent() {
        intent?.let {
            note = it.getParcelableExtra(NOTE_KEY)
        }
    }
}
