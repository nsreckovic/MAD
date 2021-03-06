package com.ns.mad_p1.view.viewpager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ns.mad_p1.R
import com.ns.mad_p1.view.fragments.AddPatientFragment
import com.ns.mad_p1.view.fragments.ListsFragment
import com.ns.mad_p1.view.fragments.ProfileFragment
import com.ns.mad_p1.view.fragments.StateFragment

class MainActivityPagerAdapter(fragmentManager: FragmentManager, private var context: Context?) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)  {

    companion object {
        private const val ITEM_COUNT = 4
        const val FRAGMENT_STATE = 0
        const val FRAGMENT_INPUT = 1
        const val FRAGMENT_LIST = 2
        const val FRAGMENT_PROFILE = 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            FRAGMENT_STATE -> StateFragment()
            FRAGMENT_INPUT -> AddPatientFragment()
            FRAGMENT_LIST -> ListsFragment()
            else -> ProfileFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FRAGMENT_STATE -> context?.getString(R.string.mi_state)
            FRAGMENT_INPUT -> context?.getString(R.string.mi_add_patient)
            FRAGMENT_LIST -> context?.getString(R.string.mi_lists)
            else -> context?.getString(R.string.mi_profile)
        }
    }

}