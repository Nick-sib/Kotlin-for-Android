package com.nickolay.kotlin_for_android.ui.note

import androidx.lifecycle.Observer
import com.nickolay.kotlin_for_android.data.NotesRepository
import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.data.model.NoteResult
import com.nickolay.kotlin_for_android.ui.base.BaseViewModel

class NoteViewModel(val notesRepository: NotesRepository): BaseViewModel<NoteViewState.Data, NoteViewState>() {

    init {
        viewStateLiveData.value = NoteViewState()
    }

    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    fun loadNote(noteId: String) {
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

    fun deleteNote() {
        pendingNote?.let {
            notesRepository.deleteNote(it.id).observeForever(object : Observer<NoteResult>{
                override fun onChanged(result: NoteResult?) {
                    result ?: return
                    pendingNote = null
                    when(result) {
                        is NoteResult.Success<*> -> viewStateLiveData.value = NoteViewState(NoteViewState.Data(isDeleted = true))
                        is NoteResult.Error -> viewStateLiveData.value = NoteViewState(error = result.error)
                    }
                    notesRepository.deleteNote(it.id).removeObserver(this)
                }

            })
        }
    }

    override fun onCleared() {
        pendingNote?.let {
            notesRepository.saveNote(it)
        }
    }

}