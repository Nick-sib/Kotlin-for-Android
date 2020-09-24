package com.nickolay.kotlin_for_android.ui.splash

import com.nickolay.kotlin_for_android.ui.base.BaseViewState

class SplashViewState (authenticated: Boolean? = null, error: Throwable? = null) : BaseViewState<Boolean?>(authenticated, error)