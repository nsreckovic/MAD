package com.ns.mad_p4.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ns.mad_p4.R
import com.ns.mad_p4.data.models.ui.WeatherUI
import com.ns.mad_p4.presentation.view.recycler.diff.WeatherDiffItemCallback
import com.ns.mad_p4.presentation.view.recycler.viewholder.WeatherViewHolder

class WeatherAdapter(
    weatherDiffItemCallback: WeatherDiffItemCallback,
    private val onItemClicked: (WeatherUI) -> Unit
) : ListAdapter<WeatherUI, WeatherViewHolder>(weatherDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.main_weather_list_item, parent, false)
        return WeatherViewHolder(view) {
            val location = getItem(it)
            onItemClicked.invoke(location)
        }
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}