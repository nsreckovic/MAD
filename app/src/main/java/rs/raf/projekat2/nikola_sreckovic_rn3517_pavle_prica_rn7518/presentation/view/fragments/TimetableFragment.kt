package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.fragments

import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.R
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_timetable.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.subject.Filter
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.contract.SubjectContract
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.recycler.adapter.SubjectAdapter
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.states.TimetableState
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.viewmodel.SubjectViewModel
import timber.log.Timber

class TimetableFragment : Fragment(R.layout.fragment_timetable) {

    private lateinit var adapter: SubjectAdapter
    private val subjectViewModel: SubjectContract.ViewModel by viewModel<SubjectViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        timetable_subjects_Rv.layoutManager = LinearLayoutManager(context)
        adapter = SubjectAdapter()
        timetable_subjects_Rv.adapter = adapter
    }

    private fun initListeners() {

        timetable_filter_Btn.setOnClickListener {
            val group = timetable_Group_Sp.selectedItem.toString()
            val day = timetable_Day_Sp.selectedItem.toString()
            val professor_subject = timetable_filter_Et.text.toString()
            val filter: Filter =
                Filter(
                    group,
                    day,
                    professor_subject
                )
            subjectViewModel.getAllFilteredSubjects(filter)
        }
    }

    private fun initObservers() {
        subjectViewModel.timetableState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
        subjectViewModel.getAllSubjects()
        subjectViewModel.fetchAllSubjects()

//        var subjects: MutableList<Subject> = mutableListOf<Subject>()
//        subjects.add(Subject("Matematicka analiza", "Vezbe", "Marija Jerotijevic", "U2", "401, 402", "14:15", "CET"))
//        subjects.add(Subject("Matematicka analiza", "Predavanja", "Neko Drugi", "U2", "401, 402", "14:15", "CET"))
//        subjects.add(Subject("Algebra", "Vezbe", "Marija Jerotijevic", "U2", "401, 402", "14:15", "CET"))
//        subjects.add(Subject("Algebra", "Predavanja", "Jelena jovanovic", "U2", "401, 402", "14:15", "CET"))

    }

    private fun renderState(state: TimetableState) {
        when (state) {
            is TimetableState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.movies)
            }
            is TimetableState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is TimetableState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is TimetableState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        timetable_LL1.isVisible = !loading
        timetable_subjects_Rv.isVisible = !loading
        timetable_filter_Et.isVisible = !loading
        timetable_filter_Btn.isVisible = !loading
        timetable_loading_Pb.isVisible = loading
    }
}