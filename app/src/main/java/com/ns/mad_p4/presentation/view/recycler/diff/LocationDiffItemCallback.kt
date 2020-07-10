package com.ns.mad_p4.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.ns.mad_p4.data.models.WeatherUI

class LocationDiffItemCallback : DiffUtil.ItemCallback<WeatherUI>() {

    override fun areItemsTheSame(oldItem: WeatherUI, newItem: WeatherUI): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WeatherUI, newItem: WeatherUI): Boolean {
        return  (oldItem.city_name == newItem.city_name) &&
                (oldItem.temp_max == newItem.temp_max) &&
                (oldItem.temp_min == newItem.temp_min) &&
                (oldItem.temp_avg == newItem.temp_avg) &&
                (oldItem.wind_speed == newItem.wind_speed) &&
                (oldItem.uv_coef == newItem.uv_coef) &&
                (oldItem.date == newItem.date) &&
                (oldItem.position == newItem.position)
    }
}