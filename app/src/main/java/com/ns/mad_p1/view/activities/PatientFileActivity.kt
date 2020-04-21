package com.ns.mad_p1.view.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ns.mad_p1.R
import com.ns.mad_p1.model.Patient
import com.ns.mad_p1.view.fragments.ListHospitalisedFragment
import kotlinx.android.synthetic.main.activity_patient_file.*
import java.text.SimpleDateFormat

class PatientFileActivity : AppCompatActivity(R.layout.activity_patient_file) {

    companion object {
        const val PATIENT_KEY = "patientKey"
    }

    private var patient: Patient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        parseIntent()
        initUI()
        initListeners()
    }

    private fun parseIntent() {
        intent?.let {
            patient = it.getParcelableExtra(PATIENT_KEY)
        }
    }

    private fun initUI() {
        if (patient != null) {
            val pattern = "dd.MM.yyyy"
            val simpleDateFormat = SimpleDateFormat(pattern)

            patient_file_Patient_Name_Et.setText(patient?.name)
            patient_file_Patient_Surname_Et.setText(patient?.surname)
            patient_file_Admission_State_Et.setText(patient?.initial_state)
            patient_file_Current_State_Et.setText(patient?.current_state)
            patient_file_Admission_Date_Tv.setText(simpleDateFormat.format(patient?.admission_date))
        }

    }

    private fun initListeners() {
        patient_file_Cancel_Btn.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        patient_file_Edit_Btn.setOnClickListener {
            val returnIntent = Intent()
            val id = patient!!.id
            val picture = patient!!.picture
            val name = patient_file_Patient_Name_Et.text.toString()
            val surname = patient_file_Patient_Surname_Et.text.toString()
            val initial_state = patient_file_Admission_State_Et.text.toString()
            val current_state = patient_file_Current_State_Et.text.toString()
            val admission_date = patient!!.admission_date
            val hospitalisation_date = patient!!.hospitalisation_date
            val dismiss_date = patient!!.dismiss_date

            if (name.isEmpty() || surname.isEmpty() || current_state.isEmpty() || current_state.isEmpty()) {
                Toast.makeText(this, R.string.input_text_error, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                val edited_patient = Patient(
                    id,
                    picture,
                    name,
                    surname,
                    initial_state,
                    current_state,
                    admission_date,
                    hospitalisation_date,
                    dismiss_date
                )
                returnIntent.putExtra(ListHospitalisedFragment.EDITED_PATIENT_KEY, edited_patient)
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            }
        }
    }

}
