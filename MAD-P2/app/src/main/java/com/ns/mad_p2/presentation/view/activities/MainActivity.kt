package com.ns.mad_p2.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import com.ns.mad_p2.R
import com.ns.mad_p2.presentation.view.adapters.MainActivityPagerAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        initViewPager()
        initNavigation()
    }

    private fun initViewPager() {
        main_ViewPager.adapter = MainActivityPagerAdapter(supportFragmentManager, this)
    }

    private fun initNavigation() {
        main_BottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.state_mi -> {
                    main_ViewPager.setCurrentItem(MainActivityPagerAdapter.FRAGMENT_TIMETABLE, false)
                }
                R.id.input_mi -> {
                    main_ViewPager.setCurrentItem(MainActivityPagerAdapter.FRAGMENT_NOTES, false)
                }
                R.id.list_mi -> {
                    main_ViewPager.setCurrentItem(MainActivityPagerAdapter.FRAGMENT_STATISTICS, false)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}
