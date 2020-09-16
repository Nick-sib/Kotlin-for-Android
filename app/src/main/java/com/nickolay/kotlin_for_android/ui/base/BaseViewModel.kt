package com.nickolay.kotlin_for_android.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<T, S: BaseViewState<T>>: ViewModel() {
    open val viewStateLiveData = MutableLiveData<S>()
    open val viewState: LiveData<S> = viewStateLiveData
}