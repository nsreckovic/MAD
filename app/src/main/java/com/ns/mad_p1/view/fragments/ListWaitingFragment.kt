package com.ns.mad_p1.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ns.mad_p1.R
import com.ns.mad_p1.view.recycler.adapter.PatientAdapter
import com.ns.mad_p1.view.recycler.diff.PatientDiffItemCallback
import com.ns.mad_p1.viewmodel.HospitalisedPatientsViewModel
import com.ns.mad_p1.viewmodel.WaitingPatientsViewModel
import kotlinx.android.synthetic.main.fragment_list_waiting.*
import kotlinx.android.synthetic.main.layout_patient_waiting_list_item.*
import timber.log.Timber

class ListWaitingFragment : Fragment(R.layout.fragment_list_waiting) {

    private val waitingPatientsViewModel: WaitingPatientsViewModel by activityViewModels()
    private val hospitalisationPatientsViewModel: HospitalisedPatientsViewModel by activityViewModels()
    private lateinit var patientAdapter: PatientAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUI()
        initObservers()
    }

    private fun initUI() {
        initListeners()
        initRecycler()
    }

    private fun initListeners() {
        listWaitingSearchEt.doAfterTextChanged {
            waitingPatientsViewModel.searchPatients(it.toString())
        }
    }

    private fun initRecycler() {
        listWaitingRv.layoutManager = LinearLayoutManager(context)
        patientAdapter = PatientAdapter(PatientDiffItemCallback(), {
            // Healthy button
            waitingPatientsViewModel.removePatient(it)
            Toast.makeText(context, R.string.healthy_success_msg, Toast.LENGTH_SHORT).show()
        }, {
            // Hospitalisation button
            hospitalisationPatientsViewModel.addPatient(it)
            waitingPatientsViewModel.removePatient(it)
            Toast.makeText(context, R.string.hospitalisation_success_msg, Toast.LENGTH_SHORT).show()
        })
        listWaitingRv.adapter = patientAdapter
    }

    private fun initObservers() {
        waitingPatientsViewModel.getPatients().observe(viewLifecycleOwner, Observer {
            patientAdapter.submitList(it)
        })
    }

}