package com.ns.mad_p4.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.ns.mad_p4.R
import com.ns.mad_p4.data.models.ui.WeatherSearchParams
import com.ns.mad_p4.presentation.contract.WeatherContract
import com.ns.mad_p4.presentation.view.recycler.adapter.WeatherAdapter
import com.ns.mad_p4.presentation.view.recycler.diff.WeatherDiffItemCallback
import com.ns.mad_p4.presentation.view.states.MainActivityWeatherState
import com.ns.mad_p4.presentation.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val weatherViewModel: WeatherContract.ViewModel by viewModel<WeatherViewModel>()
    private lateinit var adapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        main_Weathers_Rv.layoutManager = LinearLayoutManager(this)
        adapter = WeatherAdapter(WeatherDiffItemCallback()) {
            // Click
            Timber.e(it.toString())
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.WEATHER_KEY, it)
            startActivity(intent)
        }
        main_Weathers_Rv.adapter = adapter
    }

    private fun initListeners() {
        main_Search_Btn.setOnClickListener {
            val city_name = main_City_Search_Et.text.toString()
            val days = convertDaysStrToInt(main_Days_Spin.selectedItem.toString())
            if (isConnected()) weatherViewModel.fetchWeatherForCity(city_name, days)
            weatherViewModel.getWeatherForCity(WeatherSearchParams( city_name,
                SimpleDateFormat("yyyy-MM-dd").parse(SimpleDateFormat("yyyy-MM-dd").format(Date())),
                days))
        }
    }

    private fun isConnected(): Boolean {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    private fun convertDaysStrToInt(days: String): Int {
        when (days) {
            "1 Day" -> return 1
            "2 Days" -> return 2
            "3 Days" -> return 3
            "4 Days" -> return 4
            "5 Days" -> return 5
            "6 Days" -> return 6
            "7 Days" -> return 7
            "8 Days" -> return 8
            "9 Days" -> return 9
            "10 Days" -> return 10
        }
        return 0
    }

    private fun initObservers() {
        weatherViewModel.mainActivityWeatherState.observe(this, Observer {
            renderState(it)
        })
        //weatherViewModel.getWeatherForCity("", 0, 0)
    }

    private fun renderState(state: MainActivityWeatherState) {
        when (state) {
            is MainActivityWeatherState.Success -> {
                showLoadingState(false)
                Timber.e("Evo me: ${state.weather}")
                adapter.submitList(state.weather)
            }
            is MainActivityWeatherState.Error -> {
                showLoadingState(false)
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is MainActivityWeatherState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(this, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is MainActivityWeatherState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        main_City_Search_Et.isEnabled = !loading
        main_Days_Spin.isEnabled = !loading
        main_Search_Btn.isEnabled = !loading
        main_Weathers_Rv.isVisible = !loading
        main_Loading_Pb.isVisible = loading
    }

}