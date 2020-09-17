package com.nickolay.kotlin_for_android.ui.note

import androidx.lifecycle.Observer
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
        val noteLiveData = NotesRepository.getNoteByID(noteID)

        noteLiveData.observeForever(object : Observer<NoteResult> {
            override fun onChanged(result: NoteResult?) {
                result ?: return
                when(result) {
                    is NoteResult.Success<*> ->  viewStateLiveData.value = NoteViewState(note = result.data as? Note)
                    is NoteResult.Error -> viewStateLiveData.value = NoteViewState(error = result.error)
                }
                noteLiveData.removeObserver(this)
            }
        })
    }

    override fun onCleared() {
        pendingNote?.let {
            NotesRepository.saveNote(pendingNote!!)
        }
    }

}