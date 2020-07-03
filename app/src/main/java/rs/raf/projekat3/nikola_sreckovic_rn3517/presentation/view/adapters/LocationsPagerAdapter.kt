package rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.view.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.projekat3.nikola_sreckovic_rn3517.R
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.view.fragments.LocationsListFragment
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.view.fragments.LocationsMapFragment

class LocationsPagerAdapter(fragmentManager: FragmentManager, private var context: Context?) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)  {

    companion object {
        private const val ITEM_COUNT = 2
        const val FRAGMENT_LOCATIONS_MAP = 0
        const val FRAGMENT_LOCATIONS_LIST = 1
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            FRAGMENT_LOCATIONS_MAP -> LocationsMapFragment()
            else -> LocationsListFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FRAGMENT_LOCATIONS_MAP -> context?.getString(R.string.tabi_map)
            else -> context?.getString(R.string.tabi_list)
        }
    }

}