package com.ns.mad_p1.view.recycler.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ns.mad_p1.model.Patient
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_patient_waiting_list_item.*

class WaitingPatientsViewHolder(override val containerView: View, private val onHealthyBtnClicked: (Int) -> Unit, private val onHospitalisationBtnClicked: (Int) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        patientHealthyBtn.setOnClickListener {
            onHealthyBtnClicked.invoke(adapterPosition)
        }
        patientHospitalisationBtn.setOnClickListener {
            onHospitalisationBtnClicked.invoke(adapterPosition)
        }
    }

    fun bind(patient: Patient) {
        Picasso.get().load(patient.picture).into(patientPictureIv)
        patientNameTv.text = patient.name
        patientSurnameTv.text = patient.surname
        patientStateTv.text = patient.current_state
    }

}