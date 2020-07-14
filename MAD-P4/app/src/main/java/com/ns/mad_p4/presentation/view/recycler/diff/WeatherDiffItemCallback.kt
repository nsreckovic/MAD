package com.ns.mad_p4.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.ns.mad_p4.data.models.ui.WeatherUI

class WeatherDiffItemCallback : DiffUtil.ItemCallback<WeatherUI>() {

    override fun areItemsTheSame(oldItem: WeatherUI, newItem: WeatherUI): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WeatherUI, newItem: WeatherUI): Boolean {
        return  (oldItem.name == newItem.name) &&
                (oldItem.country == newItem.country) &&
                (oldItem.latitude == newItem.latitude) &&
                (oldItem.longitude == newItem.longitude) &&
                (oldItem.timezone_id == newItem.timezone_id) &&
                (oldItem.local_time == newItem.local_time) &&
                (oldItem.date == newItem.date) &&
                (oldItem.max_temp == newItem.max_temp) &&
                (oldItem.min_temp == newItem.min_temp) &&
                (oldItem.avg_temp == newItem.avg_temp) &&
                (oldItem.max_wind_speed == newItem.max_wind_speed) &&
                (oldItem.avg_visibility == newItem.avg_visibility) &&
                (oldItem.avg_humidity == newItem.avg_humidity) &&
                (oldItem.daily_chance_of_rain == newItem.daily_chance_of_rain) &&
                (oldItem.text == newItem.text) &&
                (oldItem.icon == newItem.icon) &&
                (oldItem.icon_code == newItem.icon_code) &&
                (oldItem.uv_coef == newItem.uv_coef)
    }
}