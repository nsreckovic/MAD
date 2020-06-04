package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.Subject

class SubjectDiffItemCallback : DiffUtil.ItemCallback<Subject>() {

    override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
        return  (oldItem.subject == newItem.subject) &&
                (oldItem.type == newItem.type) &&
                (oldItem.professor == newItem.professor) &&
                (oldItem.room == newItem.room) &&
                (oldItem.groups == newItem.groups) &&
                (oldItem.time == newItem.time) &&
                (oldItem.day == newItem.day)
    }
}