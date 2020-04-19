package com.ns.mad_p1.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ns.mad_p1.R
import com.ns.mad_p1.view.recycler.adapter.PatientAdapter
import com.ns.mad_p1.view.recycler.diff.PatientDiffItemCallback
import com.ns.mad_p1.viewmodel.SharedPatientViewModel
import kotlinx.android.synthetic.main.fragment_list_waiting.*
import kotlinx.android.synthetic.main.layout_patient_waiting_list_item.*
import timber.log.Timber

class ListWaitingFragment : Fragment(R.layout.fragment_list_waiting) {

    private val sharedPatientViewModel: SharedPatientViewModel by activityViewModels()
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
//        patientHealthyBtn.setOnClickListener {
//            val patients = sharedPatientViewModel.getPatients().value
//            val first = patients?.get(0)
//            Timber.e(first.toString())
//        }
    }

    private fun initRecycler() {
        listWaitingRv.layoutManager = LinearLayoutManager(context)
        patientAdapter = PatientAdapter(PatientDiffItemCallback()) {
            Timber.e("Clicked on: $it")
        }
        listWaitingRv.adapter = patientAdapter
    }

    private fun initObservers() {
        sharedPatientViewModel.getPatients().observe(viewLifecycleOwner, Observer {
            patientAdapter.submitList(it)
        })
    }

}