package com.ns.mad_p1.view.viewpager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ns.mad_p1.R
import com.ns.mad_p1.view.fragments.ListDismissedFragment
import com.ns.mad_p1.view.fragments.ListHospitalisedFragment
import com.ns.mad_p1.view.fragments.ListWaitingFragment

class ListsPagerAdapter(fragmentManager: FragmentManager, private var context: Context?) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)  {

    companion object {
        private const val ITEM_COUNT = 3
        const val FRAGMENT_WAITING_LIST = 0
        const val FRAGMENT_HOSPITALISED_LIST = 1
        const val FRAGMENT_DISMISSED_LIST = 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            FRAGMENT_WAITING_LIST -> ListWaitingFragment()
            FRAGMENT_HOSPITALISED_LIST -> ListHospitalisedFragment()
            else -> ListDismissedFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FRAGMENT_WAITING_LIST -> context?.getString(R.string.list_waiting_room)
            FRAGMENT_HOSPITALISED_LIST -> context?.getString(R.string.list_hospitalised)
            else -> context?.getString(R.string.list_dismissed)
        }
    }

}