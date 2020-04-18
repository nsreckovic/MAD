package com.ns.mad_p1.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ns.mad_p1.R
import com.ns.mad_p1.view.viewpager.MainActivityPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.viewPager
import kotlinx.android.synthetic.main.fragment_list.*
import timber.log.Timber

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initViewPager()
        initNavigation()
    }

    private fun initViewPager() {
        if (viewPager == null) Timber.e("NULL JE MAIN")
        viewPager.adapter = MainActivityPagerAdapter(supportFragmentManager, this)
    }

    private fun initNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.state_mi ->  {
                    viewPager.setCurrentItem(MainActivityPagerAdapter.FRAGMENT_STATE, false)
                }
                R.id.input_mi ->  {
                    viewPager.setCurrentItem(MainActivityPagerAdapter.FRAGMENT_INPUT, false)
                }
                R.id.list_mi ->  {
                    viewPager.setCurrentItem(MainActivityPagerAdapter.FRAGMENT_LIST, false)
                }
                R.id.profile_mi ->  {
                    viewPager.setCurrentItem(MainActivityPagerAdapter.FRAGMENT_PROFILE, false)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}
