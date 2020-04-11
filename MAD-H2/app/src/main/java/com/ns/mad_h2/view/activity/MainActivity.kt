package com.ns.mad_h2.view.activity

import android.os.Bundle
import android.text.method.MovementMethod
import android.text.method.ScrollingMovementMethod
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ns.mad_h2.R
import com.ns.mad_h2.model.Contact
import com.ns.mad_h2.view.recycler.adapter.ContactAdapter
import com.ns.mad_h2.view.recycler.diff.ContactDiffItemCallback
import com.ns.mad_h2.viewModel.ContactViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_contact_list_item.*
import timber.log.Timber
import kotlin.random.Random


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val contactViewModel: ContactViewModel by viewModels()
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        filterEt.doAfterTextChanged {
            contactViewModel.filterContacts(it.toString())
            addBtn.setEnabled(false)
            if (it.toString() == "") addBtn.setEnabled(true)
        }
        addBtn.setOnClickListener {
            if (inputEt.text.toString() != "") {
                val i = Random.nextInt(100, 1000)
                val contact = Contact(i, "https://cdn.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png","${inputEt.text}", "${inputEt.text}ic", "+381 / 6${Random.nextInt(0, 9)}-${Random.nextInt(100, 999)}-${Random.nextInt(100, 999)}", "random$i@email.com")
                Timber.e("Added contact $contact")
                contactViewModel.addContact(contact)
                inputEt.setText("")
            }
        }
    }

    private fun initRecycler() {
        gridRv.layoutManager = GridLayoutManager(this, 2)
        contactAdapter = ContactAdapter(ContactDiffItemCallback()) {
            contactViewModel.removeContact(it)
            Timber.e("Removed contact: $it")
        }
        gridRv.adapter = contactAdapter
    }

    private fun initObservers() {
        contactViewModel.getContacts().observe(this, Observer {
            contactAdapter.submitList(it)
        })
    }

}
