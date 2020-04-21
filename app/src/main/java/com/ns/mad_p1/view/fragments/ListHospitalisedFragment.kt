package com.ns.mad_p1.view.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ns.mad_p1.R
import com.ns.mad_p1.model.Patient
import com.ns.mad_p1.view.activities.PatientFileActivity
import com.ns.mad_p1.view.recycler.adapter.HospitalisedPatientsAdapter
import com.ns.mad_p1.view.recycler.diff.PatientDiffItemCallback
import com.ns.mad_p1.viewmodel.DismissedPatientsViewModel
import com.ns.mad_p1.viewmodel.HospitalisedPatientsViewModel
import kotlinx.android.synthetic.main.fragment_list_hospitalised.*
import java.util.*

class ListHospitalisedFragment : Fragment(R.layout.fragment_list_hospitalised) {

    companion object {
        const val EDITED_PATIENT_KEY = "editedPatientKey"
        const val PATIENT_RECEIVED_REQUEST_CODE = 1234
    }

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
        list_hospitalised_Search_Et.doAfterTextChanged {
            hospitalisationPatientsViewModel.searchPatients(it.toString())
        }
    }

    private fun initRecycler() {
        list_hospitalised_Rv.layoutManager = LinearLayoutManager(context)
        hospitalisedPatientsAdapter = HospitalisedPatientsAdapter(PatientDiffItemCallback(), {
            // File button
            val intent = Intent(context, PatientFileActivity::class.java)
            intent.putExtra(PatientFileActivity.PATIENT_KEY, it)
            startActivityForResult(intent, PATIENT_RECEIVED_REQUEST_CODE)

        }, {
            // Dismiss button
            val date = Date()
            it.dismiss_date = date
            hospitalisationPatientsViewModel.removePatient(it)
            dismissedPatientsViewModel.addPatient(it)
            Toast.makeText(context, R.string.list_hospitalised_dismiss_patient_success, Toast.LENGTH_SHORT).show()
        })
        list_hospitalised_Rv.adapter = hospitalisedPatientsAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PATIENT_RECEIVED_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val edited_patient = data?.getParcelableExtra<Patient>(EDITED_PATIENT_KEY)
                if (edited_patient != null) {
                    hospitalisationPatientsViewModel.editPatient(edited_patient)
                    hospitalisedPatientsAdapter.notifyDataSetChanged()
                    Toast.makeText(context, R.string.patient_file_data_saved_msg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initObservers() {
        hospitalisationPatientsViewModel.getPatients().observe(viewLifecycleOwner, Observer {
            hospitalisedPatientsAdapter.submitList(it)
        })
    }

}