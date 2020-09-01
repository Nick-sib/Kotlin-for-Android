package com.nickolay.kotlin_for_android

import android.os.Handler
import android.os.Looper


class ModelRepository{
    private var clickCount = 0

    fun doClick(onDataReadyCallback: OnDataCallback) {
        Handler(Looper.getMainLooper()).post {
            onDataReadyCallback.onDataReady(++clickCount)
        }
    }

}