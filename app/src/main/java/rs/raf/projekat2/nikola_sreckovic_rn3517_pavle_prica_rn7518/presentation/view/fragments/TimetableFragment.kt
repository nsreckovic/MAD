package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.fragments

import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.R
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_timetable.*
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.Subject
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.recycler.adapter.SubjectAdapter

class TimetableFragment : Fragment(R.layout.fragment_timetable) {

    private lateinit var adapter: SubjectAdapter

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
        var subjects: MutableList<Subject> = mutableListOf<Subject>()
        subjects.add(Subject("Matematicka analiza", "Vezbe", "Marija Jerotijevic", "U2", "401, 402", "14:15", "CET"))
        subjects.add(Subject("Matematicka analiza", "Predavanja", "Neko Drugi", "U2", "401, 402", "14:15", "CET"))
        subjects.add(Subject("Algebra", "Vezbe", "Marija Jerotijevic", "U2", "401, 402", "14:15", "CET"))
        subjects.add(Subject("Algebra", "Predavanja", "Jelena jovanovic", "U2", "401, 402", "14:15", "CET"))
        adapter.submitList(subjects)
    }
}