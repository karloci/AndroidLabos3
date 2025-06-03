package hr.tvz.android.mvpstjepanovic.instrument.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Instrument(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("wikipediaUrl")
    val wikipediaUrl: String,
) : Parcelable
