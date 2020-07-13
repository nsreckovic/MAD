package com.ns.mad_p4.presentation.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ns.mad_p4.data.models.ui.WeatherUI
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.main_weather_list_item.*
import java.text.SimpleDateFormat

class WeatherViewHolder(
    override val containerView: View,
    onItemClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        containerView.setOnClickListener {
            onItemClicked.invoke(adapterPosition)
        }
    }

    fun bind(weatherUI: WeatherUI) {
        Picasso.get().load(weatherUI.icon).into(main_weather_item_Image_Iv)
        main_weather_item_Temperature_Tv.text = weatherUI.avg_temp.toString()
        main_weather_item_City_Tv.text = weatherUI.name
        main_weather_item_Date_Tv.text = SimpleDateFormat("dd.MM.yyyy").format(weatherUI.date)
    }

}