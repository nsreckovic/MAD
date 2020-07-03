package com.ns.mad_p3.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_locations_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.ns.mad_p3.R
import com.ns.mad_p3.data.models.LocationListFilter
import com.ns.mad_p3.presentation.contract.LocationContract
import com.ns.mad_p3.presentation.contract.ModeContract
import com.ns.mad_p3.presentation.view.activities.EditLocationActivity
import com.ns.mad_p3.presentation.view.recycler.adapter.LocationAdapter
import com.ns.mad_p3.presentation.view.recycler.diff.LocationDiffItemCallback
import com.ns.mad_p3.presentation.view.states.LocationsState
import com.ns.mad_p3.presentation.viewmodel.LocationsViewModel
import com.ns.mad_p3.presentation.viewmodel.ModeViewModel
import timber.log.Timber

class LocationsListFragment : Fragment(R.layout.fragment_locations_list), AdapterView.OnItemSelectedListener  {

    private val locationsViewModel: LocationContract.ViewModel by viewModel<LocationsViewModel>()
    private val modeViewModel: ModeContract.ViewModel by viewModel<ModeViewModel>()
    private lateinit var adapter: LocationAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        if (modeViewModel.getCurrentMode()) {
            locations_list_darkmode_Fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_round_darkmode_off_primary_24))
        } else {
            locations_list_darkmode_Fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_round_darkmode_on_primary_24))
        }

        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        locations_Rv.layoutManager = LinearLayoutManager(context)
        adapter = LocationAdapter(LocationDiffItemCallback(), {
            // Edit
            Timber.e(it.toString())
            val intent = Intent(context, EditLocationActivity::class.java)
            intent.putExtra(EditLocationActivity.LOCATION_KEY, it)
            startActivity(intent)

        }, {
            // Delete
            locationsViewModel.delete(it)
        })
        locations_Rv.adapter = adapter
    }

    private fun initListeners() {
        locations_list_Search_Et.doAfterTextChanged {
            val title_content = it.toString()
            val order = locations_list_Order_Spin.selectedItem.toString()
            locationsViewModel.getAll(LocationListFilter(title_content, title_content, order))
        }
        locations_list_Order_Spin.onItemSelectedListener = this

        locations_list_darkmode_Fab.setOnClickListener {
            modeViewModel.flipMode()
        }
    }

    private fun initObservers() {
        locationsViewModel.locationsState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
        locationsViewModel.getAll(LocationListFilter("", "", "Oldest"))

        modeViewModel.getMode().observe(viewLifecycleOwner, Observer {
            if (it) locations_list_darkmode_Fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_round_darkmode_off_primary_24))
            else locations_list_darkmode_Fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_round_darkmode_on_primary_24))
        })
    }

    private fun renderState(state: LocationsState) {
        when (state) {
            is LocationsState.Success -> {
                adapter.submitList(state.locations)
            }
            is LocationsState.OperationSuccess -> {
                Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
            }
            is LocationsState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val order = locations_list_Order_Spin.getItemAtPosition(position).toString()
        val title_content = locations_list_Search_Et.text.toString()
        locationsViewModel.getAll(LocationListFilter(title_content, title_content, order))
    }

}