package com.nickolay.kotlin_for_android.ui.note

import androidx.lifecycle.Observer
import com.nickolay.kotlin_for_android.data.NotesRepository
import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.data.model.NoteResult
import com.nickolay.kotlin_for_android.ui.base.BaseViewModel

class NoteViewModel(val notesRepository: NotesRepository): BaseViewModel<NoteViewState.Data, NoteViewState>() {

    private var pendingNote: Note? = null

    init {
        viewStateLiveData.value = NoteViewState()
    }

    fun save(note: Note) {
        pendingNote = note
    }

    fun loadNote(noteId: String) {
        //здесь надо удалять обзервер?
        notesRepository.getNoteByID(noteId).observeForever(object : Observer<NoteResult>{
            override fun onChanged(result: NoteResult?) {
                result ?: return
                when (result) {
                    is NoteResult.Success<*> -> {
                            pendingNote = result.data as? Note
                            viewStateLiveData.value = NoteViewState(NoteViewState.Data(note = pendingNote))
                        }
                    is NoteResult.Error -> viewStateLiveData.value = NoteViewState(error = result.error)
                }
                notesRepository.getNoteByID(noteId).removeObserver(this)
            }

        })
    }


    override fun onCleared() {
        pendingNote?.let {
            notesRepository.saveNote(it)
        }
    }

}