package com.ns.mad_p1.view.fragments

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ns.mad_p1.R
import com.ns.mad_p1.view.recycler.adapter.DismissedPatientsAdapter
import com.ns.mad_p1.view.recycler.diff.PatientDiffItemCallback
import com.ns.mad_p1.viewmodel.DismissedPatientsViewModel
import kotlinx.android.synthetic.main.fragment_list_dismissed.*

class ListDismissedFragment : Fragment(R.layout.fragment_list_dismissed) {

    private val dismissedPatientsViewModel: DismissedPatientsViewModel by activityViewModels()
    private lateinit var dismissedPatientsAdapter: DismissedPatientsAdapter

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
        list_dismissed_Search_Et.doAfterTextChanged {
            dismissedPatientsViewModel.searchPatients(it.toString())
        }
    }

    private fun initRecycler() {
        list_dismissed_Rv.layoutManager = GridLayoutManager(context, 2)
        dismissedPatientsAdapter = DismissedPatientsAdapter(PatientDiffItemCallback())
        list_dismissed_Rv.adapter = dismissedPatientsAdapter
    }

    private fun initObservers() {
        dismissedPatientsViewModel.getPatients().observe(viewLifecycleOwner, Observer {
            dismissedPatientsAdapter.submitList(it)
        })
    }

}