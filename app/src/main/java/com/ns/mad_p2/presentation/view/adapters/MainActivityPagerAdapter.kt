package com.ns.mad_p2.presentation.view.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ns.mad_p2.R
import com.ns.mad_p2.presentation.view.fragments.NotesFragment
import com.ns.mad_p2.presentation.view.fragments.StatisticsFragment
import com.ns.mad_p2.presentation.view.fragments.TimetableFragment

class MainActivityPagerAdapter(fragmentManager: FragmentManager, private var context: Context?) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 3
        const val FRAGMENT_TIMETABLE = 0
        const val FRAGMENT_NOTES = 1
        const val FRAGMENT_STATISTICS = 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            FRAGMENT_TIMETABLE -> TimetableFragment()
            FRAGMENT_NOTES -> NotesFragment()
            else -> StatisticsFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FRAGMENT_TIMETABLE -> context?.getString(R.string.mi_timetable)
            FRAGMENT_NOTES -> context?.getString(R.string.mi_notes)
            else -> context?.getString(R.string.mi_statistics)
        }
    }

}