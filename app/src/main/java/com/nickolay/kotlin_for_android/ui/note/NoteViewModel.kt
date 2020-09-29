package com.nickolay.kotlin_for_android.ui.note

import com.nickolay.kotlin_for_android.data.NotesRepository
import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class NoteViewModel(private val notesRepository: NotesRepository): BaseViewModel<NoteData>() {

    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    fun loadNote(noteId: String)  = launch {
        try {
            notesRepository.getNoteByID(noteId)?.let {
                setData(NoteData(note = it))
            }
        } catch (e: Throwable) {
            setError(e)
        }
    }

    fun deleteNote() = launch{
        try {
            pendingNote?.let {
                notesRepository.getNoteByID(it.id)
            }
            setData(NoteData(isDeleted = true))
        } catch (e: Throwable) {
            setError(e)
        }
    }

    override fun onCleared() {
        launch {
            pendingNote?.let {
                notesRepository.saveNote(it)
            }
        }
    }

}