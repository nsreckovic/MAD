package rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.models.LocationUI

class LocationDiffItemCallback : DiffUtil.ItemCallback<LocationUI>() {

    override fun areItemsTheSame(oldItem: LocationUI, newItem: LocationUI): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LocationUI, newItem: LocationUI): Boolean {
        return  (oldItem.title == newItem.title) &&
                (oldItem.content == newItem.content) &&
                (oldItem.timestamp == newItem.timestamp)
    }
}