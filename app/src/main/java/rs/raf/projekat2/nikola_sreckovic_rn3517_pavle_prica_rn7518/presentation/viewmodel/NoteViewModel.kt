package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.R
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.Resource
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note.Note
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note.NoteFilter
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.repositories.notes.NoteRepository
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.contract.NoteContract
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.states.NewNoteState
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.states.NotesState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class NoteViewModel (
    private val noteRepository: NoteRepository
) : ViewModel(), NoteContract.ViewModel  {

    override val addNoteDone: MutableLiveData<NewNoteState> = MutableLiveData()
    override val notesState: MutableLiveData<NotesState> = MutableLiveData()
    private val subscriptions = CompositeDisposable()

    private val publishSubject: PublishSubject<NoteFilter> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                noteRepository
                    .getFilteredNotes(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    val list = (it as Resource.Success).data
                    notesState.value = NotesState.Success(list)
                },
                {
                    notesState.value = NotesState.Error("Error occurred while filtering data from DB.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

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
                    addNoteDone.value = NewNoteState.Error("Error occurred while adding new note.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun archive(note: Note) {
        val archiveStatus = if(note.archived == "true") "archiving" else "unarchiving"

        val noteToUpdate = Note(
            note.id,
            note.title,
            note.content,
            if(note.archived == "true") "false" else "true"
        )

        val subscription = noteRepository
            .update(noteToUpdate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.OperationSuccess("$archiveStatus successful")
                },
                {
                    addNoteDone.value = NewNoteState.Error("Error occurred while $archiveStatus note.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun update(note: Note) {
        val subscription = noteRepository
            .update(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.OperationSuccess("Note successfully edited.")
                },
                {
                    addNoteDone.value = NewNoteState.Error("Error occurred while editing note.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun delete(note: Note) {
        val subscription = noteRepository
            .delete(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.OperationSuccess("Note successfully deleted.")
                },
                {
                    addNoteDone.value = NewNoteState.Error("Error occurred while deleting note.")
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
                    notesState.value = NotesState.Error("Error occurred while getting data from DB.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllArchivedNotes() {
        val subscription = noteRepository
            .getAllArchived()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val list = (it as Resource.Success).data
                    notesState.value = NotesState.Success(list)
                },
                {
                    notesState.value = NotesState.Error("Error occurred while getting data from DB.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllUnarchivedNotes() {
        val subscription = noteRepository
            .getAllUnarchived()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val list = (it as Resource.Success).data
                    notesState.value = NotesState.Success(list)
                },
                {
                    notesState.value = NotesState.Error("Error occurred while getting data from DB.")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getFilteredNotes(filter: NoteFilter) {
        publishSubject.onNext(filter)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}