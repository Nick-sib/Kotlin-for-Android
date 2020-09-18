package com.nickolay.kotlin_for_android.ui.splash

import androidx.lifecycle.ViewModelProvider
import com.nickolay.kotlin_for_android.ui.base.BaseActivity
import com.nickolay.kotlin_for_android.ui.base.BaseViewModel
import com.nickolay.kotlin_for_android.ui.main.MainActivity

class SplashActivity: BaseActivity<Boolean?, SplashViewState>() {
    override val viewModel by lazy {
        ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    override val layoutRes: Int? = null

    override fun renderData(data: Boolean?) {
        data?.takeIf { it }.let {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        MainActivity.start(this)
        finish()
    }

}