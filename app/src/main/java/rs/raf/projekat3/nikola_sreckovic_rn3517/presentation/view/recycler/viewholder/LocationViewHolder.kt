package rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_location_list_item.*
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.models.LocationUI
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class LocationViewHolder(
    override val containerView: View,
    onEditBtnClicked: (Int) -> Unit,
    onDeleteBtnClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        item_location_Edit_Iv.setOnClickListener {
            onEditBtnClicked.invoke(adapterPosition)
        }
        item_location_Delete_Iv.setOnClickListener {
            onDeleteBtnClicked.invoke(adapterPosition)
        }
    }

    fun bind(locationUI: LocationUI) {
        item_location_Title_Tv.text = locationUI.title
        item_location_Content_Tv.text = locationUI.content
        item_location_Timestamp_Tv.text = SimpleDateFormat("dd.MM.yyyy HH:mm").format(locationUI.timestamp)
    }

}