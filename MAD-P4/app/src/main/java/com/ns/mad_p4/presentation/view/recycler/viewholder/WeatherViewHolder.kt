package com.ns.mad_p4.presentation.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ns.mad_p4.R
import com.ns.mad_p4.data.models.ui.WeatherUI
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.main_weather_list_item.*
import java.math.BigDecimal
import java.math.RoundingMode
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
//        Picasso.get().load(weatherUI.icon).into(main_weather_item_Image_Iv)
        main_weather_item_Image_Iv.setImageResource(iconToDrawable(weatherUI.icon.substring(weatherUI.icon.length - 7, weatherUI.icon.length - 4).toInt()))
        main_weather_item_Temperature_Tv.text = BigDecimal(weatherUI.avg_temp).setScale(1, RoundingMode.HALF_EVEN).toString() + "°C"
        main_weather_item_City_Tv.text = weatherUI.name
        main_weather_item_Date_Tv.text = SimpleDateFormat("dd.MM.yyyy").format(weatherUI.date)
    }

    private fun iconToDrawable(code: Int): Int {
        when (code) {
            113 -> return R.drawable.w113
            116 -> return R.drawable.w116
            119 -> return R.drawable.w119
            122 -> return R.drawable.w122
            143 -> return R.drawable.w143
            176 -> return R.drawable.w176
            179 -> return R.drawable.w179
            182 -> return R.drawable.w182
            185 -> return R.drawable.w185

            200 -> return R.drawable.w200
            227 -> return R.drawable.w227
            230 -> return R.drawable.w230
            248 -> return R.drawable.w248
            260 -> return R.drawable.w260
            263 -> return R.drawable.w263
            266 -> return R.drawable.w266
            281 -> return R.drawable.w281
            284 -> return R.drawable.w284
            293 -> return R.drawable.w293
            296 -> return R.drawable.w296
            299 -> return R.drawable.w299

            302 -> return R.drawable.w302
            305 -> return R.drawable.w305
            308 -> return R.drawable.w308
            311 -> return R.drawable.w311
            314 -> return R.drawable.w314
            317 -> return R.drawable.w317
            320 -> return R.drawable.w320
            323 -> return R.drawable.w323
            326 -> return R.drawable.w326
            329 -> return R.drawable.w329
            332 -> return R.drawable.w332
            335 -> return R.drawable.w335
            338 -> return R.drawable.w338
            350 -> return R.drawable.w350
            353 -> return R.drawable.w353
            356 -> return R.drawable.w356
            359 -> return R.drawable.w359
            362 -> return R.drawable.w362
            365 -> return R.drawable.w365
            368 -> return R.drawable.w368
            371 -> return R.drawable.w371
            374 -> return R.drawable.w374
            377 -> return R.drawable.w377
        }
        return 0
    }

}