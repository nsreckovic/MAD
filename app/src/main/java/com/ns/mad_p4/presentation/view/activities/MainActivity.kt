package com.ns.mad_p4.presentation.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.ns.mad_p4.R
import com.ns.mad_p4.data.models.WeatherSearchParams
import com.ns.mad_p4.presentation.contract.WeatherContract
import com.ns.mad_p4.presentation.view.recycler.adapter.WeatherAdapter
import com.ns.mad_p4.presentation.view.recycler.diff.WeatherDiffItemCallback
import com.ns.mad_p4.presentation.view.states.MainActivityWeatherState
import com.ns.mad_p4.presentation.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.*

class MainActivity : AppCompatActivity(R.layout.activity_main), AdapterView.OnItemSelectedListener {

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
        main_City_Search_Et.doAfterTextChanged {
            val city_name = it.toString()
            val days = main_Days_Spin.selectedItem.toString()
            weatherViewModel.getWeatherForCity(WeatherSearchParams(city_name, Date().time, 0, days))
        }
        main_Days_Spin.onItemSelectedListener = this
    }

    private fun initObservers() {
        weatherViewModel.mainActivityWeatherState.observe(this, Observer {
            renderState(it)
        })
        weatherViewModel.getWeatherForCity(WeatherSearchParams("", 0,0, ""))
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
        main_City_Search_Et.isVisible = !loading
        main_Weathers_Rv.isVisible = !loading
        main_Loading_Pb.isVisible = loading
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val days = main_Days_Spin.getItemAtPosition(position).toString()
        val city_name = main_City_Search_Et.text.toString()
        weatherViewModel.getWeatherForCity(WeatherSearchParams(city_name, Date().time, 0, days))
    }

}