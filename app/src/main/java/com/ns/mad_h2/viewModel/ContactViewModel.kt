package com.ns.mad_h2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ns.mad_h2.model.Contact
import kotlin.random.Random

class ContactViewModel : ViewModel() {

    private val contacts: MutableLiveData<List<Contact>> = MutableLiveData()
    private val contactsList : MutableList<Contact> = mutableListOf()

    init {
        for (i in 1..100) {
            val contact = Contact(i, "https://cdn.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png","Name $i", "Surname $i", "+381 / 6${Random.nextInt(0, 9)}-${Random.nextInt(100, 999)}-${Random.nextInt(100, 999)}", "random$i@email.com")
            contactsList.add(contact)
        }
        contacts.value = contactsList
    }

    fun getContacts(): LiveData<List<Contact>> {
        return contacts
    }

    fun filterContacts(filter: String) {
        val filteredList = contactsList.filter {
            it.name.toLowerCase().startsWith(filter.toLowerCase())
        }
        contacts.value = filteredList
    }

    fun addContact(contact: Contact) {
        contactsList.add(contact)
        val listToSubmit = mutableListOf<Contact>()
        listToSubmit.addAll(contactsList)
        contacts.value = listToSubmit
    }

    fun removeContact(contact: Contact) {
        contactsList.remove(contact)
        val listToSubmit = mutableListOf<Contact>()
        listToSubmit.addAll(contactsList)
        contacts.value = listToSubmit
    }

}