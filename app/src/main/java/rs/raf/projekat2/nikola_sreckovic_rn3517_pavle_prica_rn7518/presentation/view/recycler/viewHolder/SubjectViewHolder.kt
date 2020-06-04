package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.recycler.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_subject_list_item.*
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.subject.Subject

class SubjectViewHolder (override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind (subject: Subject) {
        item_Subject_Tv.text = subject.subject
        item_Type_Tv.text = subject.type
        item_Professor_Tv.text = subject.professor
        item_Room_Tv.text = subject.room
        item_Groups_Tv.text = subject.groups
        item_Time_Tv.text = subject.time
        item_Day_Tv.text = subject.day
    }

}