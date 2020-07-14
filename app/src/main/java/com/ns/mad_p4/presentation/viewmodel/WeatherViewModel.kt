package com.ns.mad_p4.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ns.mad_p4.data.models.ui.WeatherSearchParams
import com.ns.mad_p4.data.models.local.Resource
import com.ns.mad_p4.data.repositories.weather.WeatherRepository
import com.ns.mad_p4.presentation.contract.WeatherContract
import com.ns.mad_p4.presentation.view.states.MainActivityWeatherState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel(), WeatherContract.ViewModel {

    override val mainActivityWeatherState: MutableLiveData<MainActivityWeatherState> = MutableLiveData()
    private val subscriptions = CompositeDisposable()
    private val publishSubject: PublishSubject<WeatherSearchParams> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .distinctUntilChanged()
            .switchMap {
                weatherRepository
                    .getByCityName(it.city_name, it.date.time, it.days)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    val list = (it as Resource.Success).data
                    Timber.e("\n\nLista:\n$list\n\n")
                    mainActivityWeatherState.value = MainActivityWeatherState.Success(list)
                },
                {
                    mainActivityWeatherState.value = MainActivityWeatherState.Error("Error happened while getting data from databse.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchWeatherForCity(city_name: String, days: Int) {
        val subscription = weatherRepository
            .fetchByCityName(city_name, days)
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when (it) {
                        is Resource.Loading -> mainActivityWeatherState.value =
                            MainActivityWeatherState.Loading
                        is Resource.Success -> mainActivityWeatherState.value =
                            MainActivityWeatherState.DataFetched
                        is Resource.Error -> mainActivityWeatherState.value =
                            MainActivityWeatherState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    mainActivityWeatherState.value = MainActivityWeatherState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getWeatherForCity(params: WeatherSearchParams) {
//        val subscription = weatherRepository
//            .getByCityName(city_name, date, days)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                    val list = (it as Resource.Success).data
//                    Timber.e("\n\nLista:\n$list\n\n")
//                    mainActivityWeatherState.value = MainActivityWeatherState.Success(list)
//                },
//                {
//                    mainActivityWeatherState.value =
//                        MainActivityWeatherState.Error("Error happened while getting data from databse.")
//                    Timber.e(it)
//                }
//            )
//        subscriptions.add(subscription)
        publishSubject.onNext(params)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}