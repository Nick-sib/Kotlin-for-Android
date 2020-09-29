package com.nickolay.kotlin_for_android.data.provider

import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.data.entity.User
import com.nickolay.kotlin_for_android.data.model.NoteResult
import kotlinx.coroutines.channels.ReceiveChannel

interface DataProvider {
    fun subscribeToAllNotes() : ReceiveChannel<NoteResult>

    suspend fun getCurrentUser() : User?
    suspend fun saveNote(note: Note) : Note
    suspend fun getNoteByID(id: String) : Note?
    suspend fun deleteNote(id: String)
}