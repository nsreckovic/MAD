package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.Resource
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note.Note
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.repositories.notes.NoteRepository
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.contract.NoteContract
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.states.NewNoteState
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.states.NotesState
import timber.log.Timber

class NoteViewModel (
    private val noteRepository: NoteRepository
) : ViewModel(), NoteContract.ViewModel  {

    override val addNoteDone: MutableLiveData<NewNoteState> = MutableLiveData()
    override val notesState: MutableLiveData<NotesState> = MutableLiveData()
    private val subscriptions = CompositeDisposable()

    override fun insert(note: Note) {
        val subscription = noteRepository
            .insert(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addNoteDone.value = NewNoteState.Success
                },
                {
                    addNoteDone.value = NewNoteState.Error("Error happened while adding new note.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllNotes() {
        val subscription = noteRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val list = (it as Resource.Success).data
                    notesState.value = NotesState.Success(list)
                },
                {
                    notesState.value = NotesState.Error("Error happened while getting data from DB.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllArchivedNotes() {

    }

    override fun getAllUnarchivedNotes() {

    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}