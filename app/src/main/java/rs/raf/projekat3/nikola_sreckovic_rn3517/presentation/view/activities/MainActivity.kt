package rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat3.nikola_sreckovic_rn3517.R
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.contract.ModeContract
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.view.adapters.MainActivityPagerAdapter
import rs.raf.projekat3.nikola_sreckovic_rn3517.presentation.viewmodel.ModeViewModel

class MainActivity : AppCompatActivity() {

    private val modeViewModel: ModeContract.ViewModel by viewModel<ModeViewModel>()
    private var current_mode: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        if (modeViewModel.getCurrentMode()) setTheme(R.style.DarkTheme)
        else setTheme(R.style.LightTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        current_mode = modeViewModel.getCurrentMode()
        init()
    }

    private fun init() {
        initViewPager()
        initNavigation()
        initObservers()
    }

    private fun initViewPager() {
        main_ViewPager.adapter = MainActivityPagerAdapter(supportFragmentManager, this)
    }

    private fun initNavigation() {
        main_BottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.map_mi -> {
                    main_ViewPager.setCurrentItem(MainActivityPagerAdapter.FRAGMENT_MAP, false)
                }
                R.id.my_pins_mi -> {
                    main_ViewPager.setCurrentItem(MainActivityPagerAdapter.FRAGMENT_MY_PINS, false)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun initObservers() {
        modeViewModel.getMode().observe(this, Observer {
            if (current_mode != it) recreate()
        })
    }

}
