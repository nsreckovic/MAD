package com.ns.mad_p1.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ns.mad_p1.R
import com.ns.mad_p1.model.Patient
import com.ns.mad_p1.viewmodel.WaitingPatientsViewModel
import kotlinx.android.synthetic.main.fragment_add_patient.*
import java.util.*

class AddPatientFragment : Fragment(R.layout.fragment_add_patient) {

    private val waitingPatientsViewModel: WaitingPatientsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initListeners()
    }

    private fun initListeners() {
        add_patient_Add_Btn.setOnClickListener {

            if (add_patient_Name_Et.text.isEmpty() || add_patient_Surname_Et.text.isEmpty() || add_patient_State_Et.text.isEmpty()) {
                Toast.makeText(context, R.string.input_text_error, Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            } else {
                val uuid: UUID = UUID.randomUUID()
                val date: Date = Date()
                val patient_name = add_patient_Name_Et.text.toString()
                val patient_surname = add_patient_Surname_Et.text.toString()
                val patient_state = add_patient_State_Et.text.toString()

                val patient = Patient(
                    uuid,
                    "https://us.123rf.com/450wm/pressmaster/pressmaster1606/pressmaster160601214/59150208-pretty-patient-sitting-in-bed.jpg?ver=6",
                    patient_name,
                    patient_surname,
                    patient_state,
                    patient_state,
                    date,
                    null,
                    null
                )

                waitingPatientsViewModel.addPatient(patient)

                Toast.makeText(context, R.string.add_patient_input_success, Toast.LENGTH_SHORT).show()

                add_patient_Name_Et.setText("")
                add_patient_Surname_Et.setText("")
                add_patient_State_Et.setText("")
            }
        }
    }
}