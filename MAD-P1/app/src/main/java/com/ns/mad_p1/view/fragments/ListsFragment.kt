package com.ns.mad_p1.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ns.mad_p1.R
import com.ns.mad_p1.view.viewpager.ListsPagerAdapter
import kotlinx.android.synthetic.main.fragment_lists.*

class ListsFragment : Fragment(R.layout.fragment_lists) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initTabs()
    }

    private fun initTabs() {
        lists_ViewPager.adapter = ListsPagerAdapter(childFragmentManager, activity)
        lists_TabLayout.setupWithViewPager(lists_ViewPager)
    }

}