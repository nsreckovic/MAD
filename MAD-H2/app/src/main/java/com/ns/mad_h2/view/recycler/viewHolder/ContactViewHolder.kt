package com.ns.mad_h2.view.recycler.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ns.mad_h2.model.Contact
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_contact_list_item.*
import kotlinx.android.synthetic.main.layout_contact_list_item.view.*
import timber.log.Timber

class ContactViewHolder(override val containerView: View, private val onDeleteClicked: (Int) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        containerView.deleteIv.setOnClickListener {
            Timber.e("Delete")
            onDeleteClicked.invoke(adapterPosition)
        }
    }

    fun bind (contact: Contact) {
        Picasso.get().load(contact.picture).into(contactPictureIv)
        nameTv.text = contact.name
        surnameTv.text = contact.surname
        numberTv.text = contact.number
        emailTv.text = contact.email
    }

}