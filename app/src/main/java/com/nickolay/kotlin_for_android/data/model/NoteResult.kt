package com.nickolay.kotlin_for_android.data.model

sealed class NoteResult {
    class Success<out T>(val data: T): NoteResult()
    class Error(val error: Throwable): NoteResult()
}