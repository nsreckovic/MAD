package com.ns.mad_p3.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import com.ns.mad_p3.data.models.LocationListFilter
import com.ns.mad_p3.data.models.LocationUI
import com.ns.mad_p3.data.models.Resource
import com.ns.mad_p3.data.repositories.LocationRepository
import com.ns.mad_p3.presentation.contract.LocationContract
import com.ns.mad_p3.presentation.view.states.LocationsState
import com.ns.mad_p3.presentation.view.states.NewLocationState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class LocationsViewModel(private val locationRepository: LocationRepository) : ViewModel(), LocationContract.ViewModel   {

    override val addLocationDone: MutableLiveData<NewLocationState> = MutableLiveData()
    override val locationsState: MutableLiveData<LocationsState> = MutableLiveData()

    private val subscriptions = CompositeDisposable()
    private val publishSubject: PublishSubject<LocationListFilter> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                locationRepository
                    .getAll(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    val list = (it as Resource.Success).data
                    locationsState.value = LocationsState.Success(list)
                },
                {
                    locationsState.value = LocationsState.Error("Error occurred while filtering data from database.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    override fun insert(location: LocationUI) {
        val subscription = locationRepository
            .insert(location)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addLocationDone.value = NewLocationState.Success("Location added successfully.")
                },
                {
                    addLocationDone.value = NewLocationState.Error("Error occurred while adding new location.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun update(location: LocationUI) {
        val subscription = locationRepository
            .update(location)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    locationsState.value = LocationsState.OperationSuccess("Location successfully edited.")
                },
                {
                    locationsState.value = LocationsState.Error("Error occurred while editing location.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun delete(location: LocationUI) {
        val subscription = locationRepository
            .delete(location)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    locationsState.value = LocationsState.OperationSuccess("Location successfully deleted.")
                },
                {
                    locationsState.value = LocationsState.Error("Error occurred while deleting location.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAll(filter: LocationListFilter) {
        publishSubject.onNext(filter)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}