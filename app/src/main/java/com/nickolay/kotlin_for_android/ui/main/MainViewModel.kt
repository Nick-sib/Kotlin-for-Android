package com.nickolay.kotlin_for_android.ui.main

import androidx.lifecycle.*
import com.nickolay.kotlin_for_android.data.NotesRepository

class MainViewModel : ViewModel() {

    private val _viewStateLiveData = MutableLiveData<MainViewState>()

    init {
        NotesRepository.notesLiveData.observeForever{
            _viewStateLiveData.value = _viewStateLiveData.value?.copy(notes = it) ?: MainViewState(it)
        }

    }

    val viewState: LiveData<MainViewState> = _viewStateLiveData


}