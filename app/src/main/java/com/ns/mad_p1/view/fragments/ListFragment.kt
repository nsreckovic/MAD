package com.ns.mad_p1.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ns.mad_p1.R
import com.ns.mad_p1.view.viewpager.ListsPagerAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import timber.log.Timber

class ListFragment : Fragment(R.layout.fragment_list) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initTabs()
    }

    private fun initTabs() {
        if (listsViewPager == null) Timber.e("NULL JE")
//        listsViewPager.adapter = ListsPagerAdapter(childFragmentManager, activity)
//        listsTabLayout.setupWithViewPager(listsViewPager)
    }

}