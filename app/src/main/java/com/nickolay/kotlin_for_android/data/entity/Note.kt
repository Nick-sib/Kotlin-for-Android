package com.nickolay.kotlin_for_android.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Note(
        val id: String,
        var title: String,
        var text: String,
        var color: Int,
        var lastChanged: Date = Date()): Parcelable {


    override fun equals(other: Any?) =
        when{
            (this === other) -> true
            (javaClass != other?.javaClass) -> false
            (id != (other as Note).id) -> false
            else -> true
        }


    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + color
        return result
    }
}