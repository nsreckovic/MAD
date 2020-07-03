package com.ns.mad_p2.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.ns.mad_p2.data.models.local.note.Note

class NoteDiffItemCallback : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return  (oldItem.title == newItem.title) &&
                (oldItem.content == newItem.content) &&
                (oldItem.archived == newItem.archived)
    }

}