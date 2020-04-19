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
import com.ns.mad_p1.view.recycler.adapter.HospitalisedPatientsAdapter
import com.ns.mad_p1.view.recycler.diff.PatientDiffItemCallback
import com.ns.mad_p1.viewmodel.DismissedPatientsViewModel
import com.ns.mad_p1.viewmodel.HospitalisedPatientsViewModel
import kotlinx.android.synthetic.main.fragment_list_dismissed.*
import kotlinx.android.synthetic.main.fragment_list_hospitalised.*
import kotlinx.android.synthetic.main.fragment_list_waiting.*
import java.util.*

class ListHospitalisedFragment : Fragment(R.layout.fragment_list_hospitalised) {

    private val hospitalisationPatientsViewModel: HospitalisedPatientsViewModel by activityViewModels()
    private val dismissedPatientsViewModel: DismissedPatientsViewModel by activityViewModels()
    private lateinit var hospitalisedPatientsAdapter: HospitalisedPatientsAdapter

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
        listHospitalisedSearchEt.doAfterTextChanged {
            hospitalisationPatientsViewModel.searchPatients(it.toString())
        }
    }

    private fun initRecycler() {
        listHospitalisedRv.layoutManager = LinearLayoutManager(context)
        hospitalisedPatientsAdapter = HospitalisedPatientsAdapter(PatientDiffItemCallback(), {
            // File button

        }, {
            // Dismiss button
            val date = Date()
            it.dismiss_date = date
            hospitalisationPatientsViewModel.removePatient(it)
            dismissedPatientsViewModel.addPatient(it)
            Toast.makeText(context, R.string.dismiss_success_msg, Toast.LENGTH_SHORT).show()
        })
        listHospitalisedRv.adapter = hospitalisedPatientsAdapter
    }

    private fun initObservers() {
        hospitalisationPatientsViewModel.getPatients().observe(viewLifecycleOwner, Observer {
            hospitalisedPatientsAdapter.submitList(it)
        })
    }

}