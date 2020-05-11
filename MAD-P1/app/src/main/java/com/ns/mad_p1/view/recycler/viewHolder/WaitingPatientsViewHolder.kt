package com.ns.mad_p1.view.recycler.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ns.mad_p1.model.Patient
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_patient_waiting_list_item.*

class WaitingPatientsViewHolder(
    override val containerView: View,
    private val onHealthyBtnClicked: (Int) -> Unit,
    private val onHospitalisationBtnClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        item_waiting_Healthy_Btn.setOnClickListener {
            onHealthyBtnClicked.invoke(adapterPosition)
        }
        item_waiting_Hospitalisation_Btn.setOnClickListener {
            onHospitalisationBtnClicked.invoke(adapterPosition)
        }
    }

    fun bind(patient: Patient) {
        Picasso.get().load(patient.picture).into(item_waiting_Image_Iv)
        item_waiting_Name_Tv.text = patient.name
        item_waiting_Surname_Tv.text = patient.surname
        item_waiting_State_Tv.text = patient.current_state
    }

}