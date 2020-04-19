package com.ns.mad_p1.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.ns.mad_p1.R
import com.ns.mad_p1.viewmodel.DismissedPatientsViewModel
import com.ns.mad_p1.viewmodel.HospitalisedPatientsViewModel
import com.ns.mad_p1.viewmodel.WaitingPatientsViewModel
import kotlinx.android.synthetic.main.fragment_state.*

class StateFragment : Fragment(R.layout.fragment_state) {

    private val waitingPatientsViewModel: WaitingPatientsViewModel by activityViewModels()
    private val hospitalisedPatientsViewModel: HospitalisedPatientsViewModel by activityViewModels()
    private val dismissedPatientsViewModel: DismissedPatientsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        initObservers()
    }

    private fun initObservers() {
        waitingPatientsViewModel.getPatients().observe(viewLifecycleOwner, Observer {
            waitingNumTv.text = it.size.toString()
        })
        hospitalisedPatientsViewModel.getPatients().observe(viewLifecycleOwner, Observer {
            hospitalisedNumTv.text = it.size.toString()
        })
        dismissedPatientsViewModel.getPatients().observe(viewLifecycleOwner, Observer {
            dismissedNumTv.text = it.size.toString()
        })
    }

}