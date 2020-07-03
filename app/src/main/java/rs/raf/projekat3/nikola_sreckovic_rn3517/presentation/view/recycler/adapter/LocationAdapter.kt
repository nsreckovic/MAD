package rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat3.nikola_sreckovic_rn3517.R
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.models.LocationUI
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.view.recycler.diff.LocationDiffItemCallback
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.view.recycler.viewholder.LocationViewHolder

class LocationAdapter(
    locationDiffItemCallback: LocationDiffItemCallback,
    private val onEditBtnClicked: (LocationUI) -> Unit,
    private val onDeleteBtnClicked: (LocationUI) -> Unit
) : ListAdapter<LocationUI, LocationViewHolder>(locationDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.layout_location_list_item, parent, false)
        return LocationViewHolder(view, {
            val location = getItem(it)
            onEditBtnClicked.invoke(location)
        }, {
            val location = getItem(it)
            onDeleteBtnClicked.invoke(location)
        })
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}