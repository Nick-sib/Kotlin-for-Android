package com.nickolay.kotlin_for_android.data

import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.data.provider.DataProvider


class NotesRepository(private val dataProvider: DataProvider) {

    fun getNotes() = dataProvider.subscribeToAllNotes()

    suspend fun getCurrentUser() = dataProvider.getCurrentUser()
    suspend fun saveNote(note: Note) = dataProvider.saveNote(note)
    suspend fun getNoteByID(id: String) = dataProvider.getNoteByID(id)
    suspend fun deleteNote(id: String) = dataProvider.deleteNote(id)
}