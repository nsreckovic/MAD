package com.ns.mad_p1.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.ns.mad_p1.model.Patient

class PatientDiffItemCallback : DiffUtil.ItemCallback<Patient>()  {

    override fun areItemsTheSame(oldItem: Patient, newItem: Patient): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Patient, newItem: Patient): Boolean {
        return  oldItem.picture == newItem.picture &&
                oldItem.name == newItem.name &&
                oldItem.surname == newItem.surname &&
                oldItem.initial_state == newItem.initial_state &&
                oldItem.current_state == newItem.current_state &&
                oldItem.admission_date == newItem.admission_date &&
                oldItem.hospitalisation_date == newItem.hospitalisation_date
    }

}