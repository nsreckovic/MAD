package com.ns.mad_p2.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_notes.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.ns.mad_p2.R
import com.ns.mad_p2.data.models.local.note.NoteFilter
import com.ns.mad_p2.presentation.contract.NoteContract
import com.ns.mad_p2.presentation.view.activities.EditNoteActivity
import com.ns.mad_p2.presentation.view.activities.NewNoteActivity
import com.ns.mad_p2.presentation.view.recycler.adapter.NoteAdapter
import com.ns.mad_p2.presentation.view.recycler.diff.NoteDiffItemCallback
import com.ns.mad_p2.presentation.view.states.NotesState
import com.ns.mad_p2.presentation.viewmodel.NoteViewModel

class NotesFragment : Fragment(R.layout.fragment_notes) {

    private val noteViewModel: NoteContract.ViewModel by viewModel<NoteViewModel>()
    private lateinit var adapter: NoteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        notes_Rv.layoutManager = LinearLayoutManager(context)
        adapter = NoteAdapter(NoteDiffItemCallback(), {
            // Edit
            val intent = Intent(context, EditNoteActivity::class.java)
            intent.putExtra(EditNoteActivity.NOTE_KEY, it)
            startActivity(intent)

        }, {
            // Delete
            noteViewModel.delete(it)

        }, {
            // Archive
            noteViewModel.archive(it)


        })
        notes_Rv.adapter = adapter
    }

    private fun initListeners() {
        notes_Search_Et.doAfterTextChanged {
            val title_content = it.toString()
            val archived = notes_Archive_Sw.isChecked.toString()
            noteViewModel.getFilteredNotes(NoteFilter(title_content, archived))
        }
        notes_Archive_Sw.setOnClickListener {
            val title_content = notes_Search_Et.text.toString()
            val archived = notes_Archive_Sw.isChecked.toString()
            noteViewModel.getFilteredNotes(NoteFilter(title_content, archived))
        }
        notes_NewNote_Btn.setOnClickListener {
            val intent = Intent(context, NewNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initObservers() {
        noteViewModel.notesState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
        noteViewModel.getFilteredNotes(NoteFilter("", "false"))
    }

    private fun renderState(state: NotesState) {
        when (state) {
            is NotesState.Success -> {
                adapter.submitList(state.notes)
            }
            is NotesState.OperationSuccess -> {
                Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
            }
            is NotesState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is NotesState.DataFetched -> {
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
        }
    }

}