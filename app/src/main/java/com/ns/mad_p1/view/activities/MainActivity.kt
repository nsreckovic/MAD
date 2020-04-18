package com.ns.mad_p1.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ns.mad_p1.R
import com.ns.mad_p1.view.viewpager.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

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
        viewPager.adapter = PagerAdapter(supportFragmentManager, this)
    }

    private fun initNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.state_mi ->  {
                    viewPager.setCurrentItem(PagerAdapter.FRAGMENT_STATE, false)
                }
                R.id.input_mi ->  {
                    viewPager.setCurrentItem(PagerAdapter.FRAGMENT_INPUT, false)
                }
                R.id.list_mi ->  {
                    viewPager.setCurrentItem(PagerAdapter.FRAGMENT_LIST, false)
                }
                R.id.profile_mi ->  {
                    viewPager.setCurrentItem(PagerAdapter.FRAGMENT_PROFILE, false)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}
