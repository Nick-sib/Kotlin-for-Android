package com.nickolay.kotlin_for_android.data.entity

import android.os.Parcelable
import com.nickolay.kotlin_for_android.R
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Note(
        val id: String = genNewID(),
        val title: String = "",
        val text: String = "",
        val color: Color = Color.GREEN,
        val lastChanged: Date = Date()): Parcelable {


    override fun equals(other: Any?) =
        when{
            (this === other) -> true
            (javaClass != other?.javaClass) -> false
            (id != (other as Note).id) -> false
            else -> true
        }

    override fun hashCode() = id.hashCode()

    enum class Color(val id: Int) {
        WHITE(R.color.color_white),
        YELLOW(R.color.color_yellow),
        GREEN(R.color.color_green),
        BLUE(R.color.color_blue),
        RED(R.color.color_red),
        VIOLET(R.color.color_violet),
        PINK(R.color.color_pink)
    }

}

fun genNewID()= UUID.randomUUID().toString()
