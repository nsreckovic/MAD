package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.repositories.notes

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.datasources.local.database.NoteDao
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.Resource
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note.ChartData
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note.Note
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note.NoteEntity
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.note.NoteFilter
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.data.models.local.subject.Subject
import timber.log.Timber
import java.sql.Date

class NoteRepositoryImpl(
    private val localDataSource: NoteDao
) : NoteRepository {

    override fun insert(note: Note): Completable {
        val noteEntity = NoteEntity(note.id, note.title, note.content, note.archived, java.util.Date().time)
        return localDataSource.insert(noteEntity)
    }

    override fun update(note: Note): Completable {
        return localDataSource.update(note.id, note.title, note.content, note.archived)
    }

    override fun getAll(): Observable<Resource<List<Note>>> {
        return localDataSource
            .getAll()
            .doOnNext {
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
            }
            .map {
                val notes = it.map {
                    Note(it.id, it.title, it.content, it.archived)
                }
                Resource.Success(notes)
            }
    }

    override fun getFilteredNotes(filter: NoteFilter): Observable<Resource<List<Note>>> {
        return localDataSource
            .getFiltered(filter.title_content, filter.archived)
            .doOnNext {
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

    override fun getNotesFromLast5Days(): Observable<Resource<List<ChartData>>> {
        return localDataSource
            .getAllFromLast5Days((java.util.Date().time / 10000000 * 10000000) - 432000000)
            .doOnNext {
            }
            .map {
                val data = mutableListOf<ChartData>()
                data.add(ChartData(1,0))
                data.add(ChartData(2,0))
                data.add(ChartData(3,0))
                data.add(ChartData(4,0))
                data.add(ChartData(5,0))

                val now = java.util.Date().time / 86400000 * 86400000

                it.forEach {
                    var note_date = it.date / 86400000 * 86400000
                    if (note_date == now) data[4].num++
                    else if (note_date == now - 86400000) data[3].num++
                    else if (note_date == now - 172800000) data[2].num++
                    else if (note_date == now - 259200000) data[1].num++
                    else if (note_date == now - 345600000) data[0].num++
                }

                Resource.Success(data)
            }
    }

}