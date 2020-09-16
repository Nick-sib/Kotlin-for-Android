package com.nickolay.kotlin_for_android.data.model

sealed class NoteResult {
    data class Success<out T>(val data: T): NoteResult()
    data class Error(val error: Throwable)

}