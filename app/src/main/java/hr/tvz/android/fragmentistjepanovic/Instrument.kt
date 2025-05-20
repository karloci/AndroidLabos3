package hr.tvz.android.fragmentistjepanovic

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Instrument(
    val name: String,
    val url: String,
    val image: Int,
) : Parcelable
