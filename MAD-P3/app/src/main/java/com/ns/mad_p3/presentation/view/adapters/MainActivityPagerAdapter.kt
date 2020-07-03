package com.ns.mad_p3.presentation.view.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ns.mad_p3.R
import com.ns.mad_p3.presentation.view.fragments.MapFragment
import com.ns.mad_p3.presentation.view.fragments.MyPinsFragment

class MainActivityPagerAdapter(fragmentManager: FragmentManager, private var context: Context?) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 2
        const val FRAGMENT_MAP = 0
        const val FRAGMENT_MY_PINS = 1
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            FRAGMENT_MAP -> MapFragment()
            else -> MyPinsFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FRAGMENT_MAP -> context?.getString(R.string.mi_map)
            else -> context?.getString(R.string.mi_my_pins)
        }
    }

}