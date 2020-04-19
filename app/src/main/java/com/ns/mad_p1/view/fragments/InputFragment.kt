package com.ns.mad_p1.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ns.mad_p1.R
import com.ns.mad_p1.model.Patient
import com.ns.mad_p1.viewmodel.WaitingPatientsViewModel
import kotlinx.android.synthetic.main.fragment_input.*
import java.util.*

class InputFragment : Fragment(R.layout.fragment_input) {

    private val waitingPatientsViewModel: WaitingPatientsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initListeners()
    }

    private fun initListeners() {
        addBtn.setOnClickListener {

            if (nameEt.text.isEmpty() || surnameEt.text.isEmpty() || stateEt.text.isEmpty()) {
                Toast.makeText(context, R.string.input_text_error, Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            } else {
                val uuid: UUID = UUID.randomUUID()
                val date: Date = Date()
                val patient_name = nameEt.text.toString()
                val patient_surname = surnameEt.text.toString()
                val patient_state = stateEt.text.toString()

                val patient = Patient(
                    uuid,
                    "https://us.123rf.com/450wm/pressmaster/pressmaster1606/pressmaster160601214/59150208-pretty-patient-sitting-in-bed.jpg?ver=6",
                    patient_name,
                    patient_surname,
                    patient_state,
                    patient_state,
                    date,
                    null
                )

                waitingPatientsViewModel.addPatient(patient)

                Toast.makeText(context, R.string.input_success, Toast.LENGTH_SHORT).show()

                nameEt.setText("")
                surnameEt.setText("")
                stateEt.setText("")
            }
        }
    }
}