package com.ns.mad_p1.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ns.mad_p1.R
import com.ns.mad_p1.model.Patient
import com.ns.mad_p1.view.recycler.diff.PatientDiffItemCallback
import com.ns.mad_p1.view.recycler.viewHolder.DismissedPatientsViewHolder

class DismissedPatientsAdapter(carDiffItemCallback: PatientDiffItemCallback): ListAdapter<Patient, DismissedPatientsViewHolder>(carDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DismissedPatientsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.layout_patient_dismissed_list_item, parent, false)
        return DismissedPatientsViewHolder(containerView)
    }

    override fun onBindViewHolder(holder: DismissedPatientsViewHolder, position: Int) {
        val patient = getItem(position)
        holder.bind(patient)
    }
}