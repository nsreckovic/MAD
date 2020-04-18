package com.ns.mad_p1.view.viewpager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ns.mad_p1.R
import com.ns.mad_p1.view.fragments.InputFragment
import com.ns.mad_p1.view.fragments.ListFragment
import com.ns.mad_p1.view.fragments.ProfileFragment
import com.ns.mad_p1.view.fragments.StateFragment

class PagerAdapter(fragmentManager: FragmentManager, private var context: Context) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)  {

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
            FRAGMENT_INPUT -> InputFragment()
            FRAGMENT_LIST -> ListFragment()
            else -> ProfileFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FRAGMENT_STATE -> context.getString(R.string.state_mi)
            FRAGMENT_INPUT -> context.getString(R.string.input_mi)
            FRAGMENT_LIST -> context.getString(R.string.list_mi)
            else -> context.getString(R.string.profile_mi)
        }
    }

}