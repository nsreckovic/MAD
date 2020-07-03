package com.ns.mad_p2.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import com.ns.mad_p2.data.models.local.subject.Filter
import com.ns.mad_p2.data.models.local.Resource
import com.ns.mad_p2.data.repositories.subjects.SubjectRepository
import com.ns.mad_p2.presentation.contract.SubjectContract
import com.ns.mad_p2.presentation.view.states.TimetableState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SubjectViewModel (
    private val subjectRepository: SubjectRepository
) : ViewModel(), SubjectContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val timetableState: MutableLiveData<TimetableState> = MutableLiveData()

    private val publishSubject: PublishSubject<Filter> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                subjectRepository
                    .getAllFilteredSubjects(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    val list = (it as Resource.Success).data
                    timetableState.value = TimetableState.Success(list)
                },
                {
                    timetableState.value = TimetableState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchAllSubjects() {
        val subscription = subjectRepository
            .fetchAll()
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> timetableState.value = TimetableState.Loading
                        is Resource.Success -> timetableState.value = TimetableState.DataFetched
                        is Resource.Error -> timetableState.value = TimetableState.Error("Error occurred while fetching data from the server.")
                    }
                },
                {
                    timetableState.value = TimetableState.Error("Error occurred while fetching data from the server.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllSubjects() {
        val subscription = subjectRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val list = (it as Resource.Success).data
                    timetableState.value = TimetableState.Success(list)
                },
                {
                    timetableState.value = TimetableState.Error("Error occurred while getting data from the database.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllFilteredSubjects(filter: Filter) {
        publishSubject.onNext(filter)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}