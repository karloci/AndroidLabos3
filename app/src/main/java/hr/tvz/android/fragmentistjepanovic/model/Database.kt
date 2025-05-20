package hr.tvz.android.fragmentistjepanovic.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Instrument::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun instrumentDao(): InstrumentDao
}