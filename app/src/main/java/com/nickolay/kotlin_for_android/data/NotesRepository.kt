package com.nickolay.kotlin_for_android.data

import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.data.provider.DataProvider


class NotesRepository(val dataProvider: DataProvider) {
    fun getCurrentUser() = dataProvider.getCurrentUser()
    fun getNotes() = dataProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = dataProvider.saveNote(note)
    fun getNoteByID(id: String) = dataProvider.getNoteByID(id)
}