package com.ns.mad_p2.presentation.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_statistics.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.ns.mad_p2.R
import com.ns.mad_p2.presentation.contract.NoteContract
import com.ns.mad_p2.presentation.view.states.StatisticsState
import com.ns.mad_p2.presentation.viewmodel.NoteViewModel

class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    private val noteViewModel: NoteContract.ViewModel by viewModel<NoteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onResume() {
        super.onResume()
        init()
    }

    private fun init() {
        initObservers()
    }

    private fun initObservers() {
        noteViewModel.statsState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
        noteViewModel.getNotesFromLast5Days()
    }

    private fun renderState(state: StatisticsState) {
        when (state) {
            is StatisticsState.StatsState -> {
                var max = 0
                state.data.forEach {
                    if (it.num > max) max = it.num
                }

                var percentage1 = if (state.data[0].num != 0 && max != 0) state.data[0].num.toDouble() / max * 1.0 else 0.01
                var percentage2 = if (state.data[1].num != 0 && max != 0) state.data[1].num.toDouble() / max * 1.0 else 0.01
                var percentage3 = if (state.data[2].num != 0 && max != 0) state.data[2].num.toDouble() / max * 1.0 else 0.01
                var percentage4 = if (state.data[3].num != 0 && max != 0) state.data[3].num.toDouble() / max * 1.0 else 0.01
                var percentage5 = if (state.data[4].num != 0 && max != 0) state.data[4].num.toDouble() / max * 1.0 else 0.01

                statistics_bar1_Cv.percentage = percentage1
                statistics_bar1_Cv.invalidate()
                statistics_num1_Tv.text = state.data[0].num.toString()

                statistics_bar2_Cv.percentage = percentage2
                statistics_bar2_Cv.invalidate()
                statistics_num2_Tv.text = state.data[1].num.toString()

                statistics_bar3_Cv.percentage = percentage3
                statistics_bar3_Cv.invalidate()
                statistics_num3_Tv.text = state.data[2].num.toString()

                statistics_bar4_Cv.percentage = percentage4
                statistics_bar4_Cv.invalidate()
                statistics_num4_Tv.text = state.data[3].num.toString()

                statistics_bar5_Cv.percentage = percentage5
                statistics_bar5_Cv.invalidate()
                statistics_num5_Tv.text = state.data[4].num.toString()

            }
            is StatisticsState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}