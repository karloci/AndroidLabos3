package hr.tvz.android.fragmentistjepanovic.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Instrument(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo val name: String,
    @ColumnInfo val url: String,
    @ColumnInfo val image: String,
): Parcelable