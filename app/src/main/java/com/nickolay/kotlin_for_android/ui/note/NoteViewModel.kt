package com.nickolay.kotlin_for_android.ui.note

import androidx.lifecycle.ViewModel
import com.nickolay.kotlin_for_android.data.NotesRepository
import com.nickolay.kotlin_for_android.data.entity.Note

class NoteViewModel(private val repository: NotesRepository = NotesRepository) : ViewModel() {
    private var pendingNote: Note? = null

    fun saveChanges(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        pendingNote?.let {
            repository.saveNote(pendingNote!!)
        }
    }

}