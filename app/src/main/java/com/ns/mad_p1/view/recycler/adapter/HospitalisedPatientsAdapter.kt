package com.ns.mad_p1.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ns.mad_p1.R
import com.ns.mad_p1.model.Patient
import com.ns.mad_p1.view.recycler.diff.PatientDiffItemCallback
import com.ns.mad_p1.view.recycler.viewHolder.HospitalisedPatientsViewHolder

class HospitalisedPatientsAdapter(carDiffItemCallback: PatientDiffItemCallback, private val onBtnPatientFileClicked: (Patient) -> Unit, private val onBtnDismissClicked: (Patient) -> Unit): ListAdapter<Patient, HospitalisedPatientsViewHolder>(carDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalisedPatientsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.layout_patient_hospitalised_list_item, parent, false)

        return HospitalisedPatientsViewHolder(containerView, {
            val patient = getItem(it)
            onBtnPatientFileClicked.invoke(patient)
        }, {
            val patient = getItem(it)
            onBtnDismissClicked.invoke(patient)
        })
    }

    override fun onBindViewHolder(holder: HospitalisedPatientsViewHolder, position: Int) {
        val patient = getItem(position)
        holder.bind(patient)
    }

}