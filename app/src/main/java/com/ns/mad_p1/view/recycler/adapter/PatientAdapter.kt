package com.ns.mad_p1.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ns.mad_p1.R
import com.ns.mad_p1.model.Patient
import com.ns.mad_p1.view.recycler.diff.PatientDiffItemCallback
import com.ns.mad_p1.view.recycler.viewHolder.PatientWaitingViewHolder

class PatientAdapter(carDiffItemCallback: PatientDiffItemCallback, private val onPatientClicked: (Patient) -> Unit): ListAdapter<Patient, PatientWaitingViewHolder>(carDiffItemCallback) {

    // kada adapter kreira ViewHolder
    // poziva se na pocetku, kada napravi sve ViewHoldere

    // moramo da prosledimo neku lambdu koja ce nam vratiti poziciju da bismo znali koji item da vratimo
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientWaitingViewHolder {
        // xml mora da se instancira! kada smo pravili activity, IDE je to radio za nas, ali
        // ovde moramo rucno. Koristimo LayoutInflater

        val layoutInflater = LayoutInflater.from(parent.context)
        // trenutno ne moramo da znamo sta su parent i false
        val containerView = layoutInflater.inflate(R.layout.layout_patient_waiting_list_item, parent, false)

        return PatientWaitingViewHolder(containerView) {
            val car = getItem(it)
            onPatientClicked.invoke(car)
        }
    }

    // kada binduje podatak za ViewHolder
    // poziva se svaki put kada treba zameniti podatke u ViewHolderu
    override fun onBindViewHolder(holder: PatientWaitingViewHolder, position: Int) {
        val car = getItem(position)
        holder.bind(car)
    }
}