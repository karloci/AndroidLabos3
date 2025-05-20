package hr.tvz.android.fragmentistjepanovic.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface InstrumentDao {
    @Query("SELECT * FROM Instrument ORDER BY id DESC")
    fun getAll(): MutableList<Instrument>

    @Insert
    fun insertOne(todo: Instrument)

    @Delete
    fun delete(todo: Instrument)
}