package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.repositories.notes

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.datasources.local.database.NoteDao
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.Resource
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note.Note
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note.NoteEntity
import timber.log.Timber

class NoteRepositoryImpl(
    private val localDataSource: NoteDao
) : NoteRepository {

    override fun insert(note: Note): Completable {
        val noteEntity = NoteEntity(note.id, note.title, note.content, note.archived)
        return localDataSource.insert(noteEntity)
    }

    override fun update(note: Note): Completable {
        return localDataSource.update(note.id, note.title, note.content, note.archived)
    }

    override fun getAll(): Observable<Resource<List<Note>>> {
        return localDataSource
            .getAll()
            .doOnNext {
                Timber.e("Citanje iz baze (notes)")
            }
            .map {
                val notes = it.map {
                    Note(it.id, it.title, it.content, it.archived)
                }
                Resource.Success(notes)
            }
    }

    override fun getAllArchived(): Observable<Resource<List<Note>>> {
        return localDataSource
            .getAllArchived()
            .doOnNext {
                Timber.e("Citanje iz baze (notes)")
            }
            .map {
                val notes = it.map {
                    Note(it.id, it.title, it.content, it.archived)
                }
                Resource.Success(notes)
            }
    }

    override fun getAllUnarchived(): Observable<Resource<List<Note>>> {
        return localDataSource
            .getAllUnarchived()
            .doOnNext {
                Timber.e("Citanje iz baze (notes)")
            }
            .map {
                val notes = it.map {
                    Note(it.id, it.title, it.content, it.archived)
                }
                Resource.Success(notes)
            }
    }

    override fun delete(note: Note): Completable {
        return localDataSource.delete(note.id)
    }

}