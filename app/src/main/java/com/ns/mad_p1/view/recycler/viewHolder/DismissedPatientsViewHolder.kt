package com.ns.mad_p1.view.recycler.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ns.mad_p1.model.Patient
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_patient_dismissed_list_item.*
import java.text.SimpleDateFormat

class DismissedPatientsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(patient: Patient) {
        Picasso.get().load(patient.picture).into(item_dismissed_Image_Iv)
        item_dismissed_Name_Tv.text = patient.name
        item_dismissed_Surname_Tv.text = patient.surname
        val pattern = "dd.MM.yyyy"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val dismiss_date: String = simpleDateFormat.format(patient.dismiss_date)
        item_dismissed_Date_Tv.text = "Otpusten: $dismiss_date"
    }

}