package com.nickolay.kotlin_for_android.data

import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.data.provider.DataProvider
import com.nickolay.kotlin_for_android.data.provider.FirestoreProvider


object NotesRepository{

    private val dataProvider: DataProvider = FirestoreProvider()

    fun getNotes() = dataProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = dataProvider.saveNote(note)
    fun getNoteByID(id: String) = dataProvider.getNoteByID(id)

}