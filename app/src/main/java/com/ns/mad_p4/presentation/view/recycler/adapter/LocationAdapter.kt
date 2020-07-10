package com.ns.mad_p4.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ns.mad_p4.R
import com.ns.mad_p4.data.models.WeatherUI
import com.ns.mad_p4.presentation.view.recycler.diff.LocationDiffItemCallback
import com.ns.mad_p4.presentation.view.recycler.viewholder.LocationViewHolder

class LocationAdapter(
    locationDiffItemCallback: LocationDiffItemCallback,
    private val onItemClicked: (WeatherUI) -> Unit
) : ListAdapter<WeatherUI, LocationViewHolder>(locationDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.main_weather_list_item, parent, false)
        return LocationViewHolder(view) {
            val location = getItem(it)
            onItemClicked.invoke(location)
        }
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}