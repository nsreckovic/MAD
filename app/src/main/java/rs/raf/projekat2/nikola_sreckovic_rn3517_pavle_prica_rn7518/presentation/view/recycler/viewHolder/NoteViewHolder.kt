package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.recycler.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_notes_list_item.*
import kotlinx.android.synthetic.main.layout_subject_list_item.*
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.R
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note.Note
import timber.log.Timber

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