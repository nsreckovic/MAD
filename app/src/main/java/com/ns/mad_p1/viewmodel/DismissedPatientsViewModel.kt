package com.ns.mad_p1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ns.mad_p1.model.Patient

class DismissedPatientsViewModel : ViewModel() {

    private val patients: MutableLiveData<List<Patient>> = MutableLiveData()
    private val patientList : MutableList<Patient> = mutableListOf()

    fun getPatients(): LiveData<List<Patient>> {
        return patients
    }

    fun addPatient(patient: Patient) {
        patientList.add(patient)
        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(patientList)
        patients.value = listToSubmit
    }

    fun removePatient(patient: Patient) {
        patientList.remove(patient)
        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(patientList)
        patients.value = listToSubmit
    }

    fun searchPatients(filter: String) {
        val filteredList = patientList.filter {
            it.name.toLowerCase().startsWith(filter.toLowerCase()) || it.surname.toLowerCase().startsWith(filter.toLowerCase())
        }
        patients.value = filteredList
    }

}