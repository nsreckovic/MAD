package com.ns.mad_p2.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ns.mad_p2.R
import com.ns.mad_p2.data.models.local.subject.Subject
import com.ns.mad_p2.presentation.view.recycler.diff.SubjectDiffItemCallback
import com.ns.mad_p2.presentation.view.recycler.viewHolder.SubjectViewHolder

class SubjectAdapter : ListAdapter<Subject, SubjectViewHolder>(SubjectDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.layout_subject_list_item, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}