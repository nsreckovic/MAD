package rs.raf.projekat3.nikola_sreckovic_rn3517.data.datasources.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat3.nikola_sreckovic_rn3517.data.models.LocationEntity

@Dao
abstract class LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: LocationEntity): Completable

    @Query("UPDATE locations SET title = :title, content = :content, longitude = :longitude, latitude = :latitude WHERE id = :id")
    abstract fun update(id: Int, title: String, content: String, latitude: Double, longitude: Double): Completable

    @Query("SELECT * FROM locations WHERE title LIKE :title OR content LIKE :content ORDER BY CASE WHEN :order = 'Oldest' THEN timestamp END ASC, CASE WHEN :order = 'Newest' THEN timestamp END DESC")
    abstract fun getAll(title: String, content: String, order: String): Observable<List<LocationEntity>>

    @Query("DELETE FROM locations WHERE id = :id")
    abstract fun delete(id: Int): Completable

}