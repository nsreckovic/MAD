package com.ns.mad_h2.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.ns.mad_h2.model.Contact

class ContactDiffItemCallback : DiffUtil.ItemCallback<Contact>(){

    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return  oldItem.name == newItem.name &&
                oldItem.surname == newItem.surname &&
                oldItem.number == newItem.number &&
                oldItem.email == newItem.email
    }
    
}