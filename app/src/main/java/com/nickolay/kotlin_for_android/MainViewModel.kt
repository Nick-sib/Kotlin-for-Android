package com.nickolay.kotlin_for_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _viewData = MutableLiveData<String>().apply {
        value = "Кликайте по кнопке"
    }
    val viewData: LiveData<String> = _viewData
}