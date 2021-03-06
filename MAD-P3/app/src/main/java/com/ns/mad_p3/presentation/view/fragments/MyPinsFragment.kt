package com.ns.mad_p3.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_my_pins.*
import com.ns.mad_p3.R
import com.ns.mad_p3.presentation.view.adapters.LocationsPagerAdapter

class MyPinsFragment : Fragment(R.layout.fragment_my_pins) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initTabs()
    }

    private fun initTabs() {
        locations_ViewPager.adapter = LocationsPagerAdapter(childFragmentManager, activity)
        locations_TabLayout.setupWithViewPager(locations_ViewPager)
    }

}