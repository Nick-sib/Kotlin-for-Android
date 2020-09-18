package com.nickolay.kotlin_for_android.data.provider

import androidx.lifecycle.LiveData
import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.data.entity.User
import com.nickolay.kotlin_for_android.data.model.NoteResult

interface DataProvider {

    fun getCurrentUser(): LiveData<User?>
    fun subscribeToAllNotes() : LiveData<NoteResult>
    fun saveNote(note: Note) : LiveData<NoteResult>
    fun getNoteByID(id: String) : LiveData<NoteResult>
}