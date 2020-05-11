package com.ns.mad_p1.view.recycler.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ns.mad_p1.model.Patient
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_patient_hospitalised_list_item.*

class HospitalisedPatientsViewHolder(
    override val containerView: View,
    private val onPatientFileBtnClicked: (Int) -> Unit,
    private val onDismissBtnClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        item_hospitalised_File_Btn.setOnClickListener {
            onPatientFileBtnClicked.invoke(adapterPosition)
        }
        item_hospitalised_Dismiss_Btn.setOnClickListener {
            onDismissBtnClicked.invoke(adapterPosition)
        }
    }

    fun bind(patient: Patient) {
        Picasso.get().load(patient.picture).into(item_hospitalised_Image_Iv)
        item_hospitalised_Name_Tv.text = patient.name
        item_hospitalised_Surname_Tv.text = patient.surname
    }

}