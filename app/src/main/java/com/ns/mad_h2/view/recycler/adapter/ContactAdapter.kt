package com.ns.mad_h2.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ns.mad_h2.R
import com.ns.mad_h2.model.Contact
import com.ns.mad_h2.view.recycler.diff.ContactDiffItemCallback
import com.ns.mad_h2.view.recycler.viewHolder.ContactViewHolder
import timber.log.Timber

class ContactAdapter(contactDiffItemCallback: ContactDiffItemCallback, private val onDeleterClicked: (Contact) -> Unit): ListAdapter<Contact, ContactViewHolder>(contactDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.layout_contact_list_item, parent, false)
        return ContactViewHolder(containerView) {
            Timber.e("Clicked on $it")
            val contact = getItem(it)
            onDeleterClicked.invoke(contact)
        }
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bind(contact)
    }
}