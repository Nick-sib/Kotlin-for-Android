package com.nickolay.kotlin_for_android

import androidx.lifecycle.*

class MainViewModel: ViewModel() {

    private var repoModel: ModelRepository = ModelRepository()

    private val _viewData = MutableLiveData<String>().apply {
        value = "Кликайте по кнопке"
    }

    private val onDataReadyCallback = object : OnDataCallback {
        override fun onDataReady(data: Int) {
            _viewData.value = "Кликнуто ($data)"
        }
    }

    val viewData: LiveData<String> = _viewData

    fun doClick(){
        repoModel.doClick(onDataReadyCallback)
    }
}