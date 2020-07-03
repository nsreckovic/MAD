package com.ns.mad_p2.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_note.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.ns.mad_p2.R
import com.ns.mad_p2.data.models.local.note.Note
import com.ns.mad_p2.presentation.contract.NoteContract
import com.ns.mad_p2.presentation.viewmodel.NoteViewModel

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
            if (!title.isBlank()) {
                if (!content.isBlank()) {
                    if (title != note.title || content != note.content) {
                        note.title = title
                        note.content = content
                        noteViewModel.update(note)
                    }
                    finish()
                } else Toast.makeText(this, R.string.edit_note_content_error, Toast.LENGTH_SHORT).show()
            } else Toast.makeText(this, R.string.edit_note_title_error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun parseIntent() {
        intent?.let {
            note = it.getParcelableExtra(NOTE_KEY)
        }
    }
}
