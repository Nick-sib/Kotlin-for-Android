package com.nickolay.kotlin_for_android.ui.note

import com.nickolay.kotlin_for_android.data.NotesRepository
import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.data.model.NoteResult
import com.nickolay.kotlin_for_android.ui.base.BaseViewModel

class NoteViewModel: BaseViewModel<Note?, NoteViewState>() {

    private var pendingNote: Note? = null

    init {
        viewStateLiveData.value = NoteViewState()
    }

    fun saveChanges(note: Note) {
        pendingNote = note
    }

    fun loadNote(noteID: String){
        //TODO: Удалить обзервер 2:17:00
        NotesRepository.getNoteByID(noteID).observeForever { result ->
            result ?: return@observeForever
            when(result){
                is NoteResult.Success<*> ->  viewStateLiveData.value = NoteViewState(note = result.data as? Note)
                is NoteResult.Error -> viewStateLiveData.value = NoteViewState(error = result.error)
            }
        }
    }

    override fun onCleared() {
        pendingNote?.let {
            NotesRepository.saveNote(pendingNote!!)
        }
    }

}