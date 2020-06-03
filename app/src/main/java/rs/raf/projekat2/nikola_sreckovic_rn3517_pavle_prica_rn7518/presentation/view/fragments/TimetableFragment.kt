package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.fragments

import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.R
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_timetable.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.Subject
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.contract.SubjectContract
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.recycler.adapter.SubjectAdapter
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.viewmodel.SubjectViewModel

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

    }

    private fun initObservers() {
        subjectViewModel.subjects.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        subjectViewModel.fetchAllSubjects()

//        var subjects: MutableList<Subject> = mutableListOf<Subject>()
//        subjects.add(Subject("Matematicka analiza", "Vezbe", "Marija Jerotijevic", "U2", "401, 402", "14:15", "CET"))
//        subjects.add(Subject("Matematicka analiza", "Predavanja", "Neko Drugi", "U2", "401, 402", "14:15", "CET"))
//        subjects.add(Subject("Algebra", "Vezbe", "Marija Jerotijevic", "U2", "401, 402", "14:15", "CET"))
//        subjects.add(Subject("Algebra", "Predavanja", "Jelena jovanovic", "U2", "401, 402", "14:15", "CET"))

    }
}