package com.ns.mad_p1.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ns.mad_p1.R
import com.ns.mad_p1.model.Patient
import com.ns.mad_p1.view.recycler.diff.PatientDiffItemCallback
import com.ns.mad_p1.view.recycler.viewHolder.WaitingPatientsViewHolder

class WaitingPatientsAdapter(
    carDiffItemCallback: PatientDiffItemCallback,
    private val onBtnHealthyClicked: (Patient) -> Unit,
    private val onBtnHospitalisationClicked: (Patient) -> Unit
) : ListAdapter<Patient, WaitingPatientsViewHolder>(carDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaitingPatientsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView =
            layoutInflater.inflate(R.layout.layout_patient_waiting_list_item, parent, false)

        return WaitingPatientsViewHolder(containerView, {
            val patient = getItem(it)
            onBtnHealthyClicked.invoke(patient)
        }, {
            val patient = getItem(it)
            onBtnHospitalisationClicked.invoke(patient)
        })
    }

    override fun onBindViewHolder(holderWaitingPatients: WaitingPatientsViewHolder, position: Int) {
        val patient = getItem(position)
        holderWaitingPatients.bind(patient)
    }

}