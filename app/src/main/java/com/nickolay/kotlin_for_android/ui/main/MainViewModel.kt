package com.nickolay.kotlin_for_android.ui.main

import androidx.lifecycle.*
import com.nickolay.kotlin_for_android.data.NotesRepository
import com.nickolay.kotlin_for_android.ui.adapter.NotesRVAdapter

class MainViewModel: ViewModel() {

    val adapter = NotesRVAdapter()

    private val _viewStateLiveData = MutableLiveData<MainViewState>().apply {
        value = MainViewState(NotesRepository.notes)
    }

    val viewState: LiveData<MainViewState> = _viewStateLiveData


}