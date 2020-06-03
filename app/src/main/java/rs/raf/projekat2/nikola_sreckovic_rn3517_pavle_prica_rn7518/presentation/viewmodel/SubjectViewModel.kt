package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.Subject
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.remote.Resource
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.repositories.subjects.SubjectRepository
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.contract.SubjectContract
import timber.log.Timber

class SubjectViewModel (
    private val subjectRepository: SubjectRepository
) : ViewModel(), SubjectContract.ViewModel {

    override val subjects: MutableLiveData<List<Subject>> = MutableLiveData()
    private val subscriptions = CompositeDisposable()

    override fun fetchAllSubjects() {
        val subscription = subjectRepository
            .fetchAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    subjects.value = it
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

}