package com.ns.mad_p2.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ns.mad_p2.R
import com.ns.mad_p2.data.models.local.note.Note
import com.ns.mad_p2.presentation.view.recycler.diff.NoteDiffItemCallback
import com.ns.mad_p2.presentation.view.recycler.viewHolder.NoteViewHolder

class NoteAdapter(
    noteDiffItemCallback: NoteDiffItemCallback,
    private val onEditBtnClicked: (Note) -> Unit,
    private val onDeleteBtnClicked: (Note) -> Unit,
    private val onArchiveBtnClicked: (Note) -> Unit
) : ListAdapter<Note, NoteViewHolder>(noteDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.layout_notes_list_item, parent, false)
        return NoteViewHolder(view, {
            val note = getItem(it)
            onEditBtnClicked.invoke(note)
        }, {
            val note = getItem(it)
            onDeleteBtnClicked.invoke(note)
        }, {
            val note = getItem(it)
            onArchiveBtnClicked.invoke(note)
        })
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}