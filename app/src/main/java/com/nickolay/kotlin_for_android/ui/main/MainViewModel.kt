package com.nickolay.kotlin_for_android.ui.main

import androidx.lifecycle.*
import com.nickolay.kotlin_for_android.data.NotesRepository
import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.data.model.NoteResult
import com.nickolay.kotlin_for_android.ui.base.BaseViewModel

class MainViewModel(notesRepository: NotesRepository) : BaseViewModel<List<Note>?, MainViewState>() {

    private val repositoryNotes = notesRepository.getNotes()

    private val notesObserver = Observer<NoteResult> {
        it ?: return@Observer
        when (it){
            is NoteResult.Success<*> -> viewStateLiveData.value = MainViewState(notes = it.data as? List<Note> )
            is NoteResult.Error -> viewStateLiveData.value = MainViewState(error = it.error)
        }
    }

    init {
        viewStateLiveData.value = MainViewState()
        repositoryNotes.observeForever (notesObserver)

    }

    override fun onCleared() {
        super.onCleared()
        repositoryNotes.removeObserver(notesObserver)
    }
}