package com.nickolay.kotlin_for_android.ui.splash

import com.nickolay.kotlin_for_android.data.NotesRepository
import com.nickolay.kotlin_for_android.data.errors.NoAuthException
import com.nickolay.kotlin_for_android.ui.base.BaseViewModel

class SplashViewModel(): BaseViewModel<Boolean?, SplashViewState>(){
    fun requestUser(){
        //TODO: Add clearing observeForever metod
        NotesRepository.getCurrentUser().observeForever{
            viewStateLiveData.value =
                    it ?.let { SplashViewState(authenticated = true) }
                    ?: SplashViewState(error = NoAuthException())
        }
    }
}