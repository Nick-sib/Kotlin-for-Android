package com.nickolay.kotlin_for_android.ui.splash

import androidx.lifecycle.Observer
import com.nickolay.kotlin_for_android.data.NotesRepository
import com.nickolay.kotlin_for_android.data.entity.User
import com.nickolay.kotlin_for_android.data.errors.NoAuthException
import com.nickolay.kotlin_for_android.ui.base.BaseViewModel

class SplashViewModel(): BaseViewModel<Boolean?, SplashViewState>(){
    fun requestUser() {
        NotesRepository.getCurrentUser().observeForever(object : Observer<User?> {
            override fun onChanged(result: User?) {
                viewStateLiveData.value =
                        result?.let { SplashViewState(authenticated = true) }
                                ?: SplashViewState(error = NoAuthException())

                NotesRepository.getCurrentUser().removeObserver(this)
            }
        })
    }
}