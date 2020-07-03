package com.ns.mad_p2.presentation.view.recycler.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_notes_list_item.*
import com.ns.mad_p2.R
import com.ns.mad_p2.data.models.local.note.Note

class NoteViewHolder(
    override val containerView: View,
    private val onEditBtnClicked: (Int) -> Unit,
    private val onDeleteBtnClicked: (Int) -> Unit,
    private val onArchiveBtnClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    init {
        item_notes_Edit_Iv.setOnClickListener {
            onEditBtnClicked.invoke(adapterPosition)
        }
        item_notes_Delete_Iv.setOnClickListener {
            onDeleteBtnClicked.invoke(adapterPosition)
        }
        item_notes_Archive_Iv.setOnClickListener {
            onArchiveBtnClicked.invoke(adapterPosition)
        }
    }

    fun bind(note: Note) {
        item_notes_Title_Tv.text = note.title
        item_notes_Content_Tv.text = note.content

        if (note.archived == "true") item_notes_Archive_Iv.setImageResource(R.drawable.ic_unarchive_24dp)
        else item_notes_Archive_Iv.setImageResource(R.drawable.ic_archive_24dp)
    }

}