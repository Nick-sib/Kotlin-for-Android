package com.nickolay.kotlin_for_android.ui.splash

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.nickolay.kotlin_for_android.ui.base.BaseActivity
import com.nickolay.kotlin_for_android.ui.main.MainActivity

class SplashActivity: BaseActivity<Boolean?, SplashViewState>() {
    override val viewModel by lazy {
        ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    override val layoutRes: Int? = null

    override fun renderData(data: Boolean?) {
        data?.takeIf { it }.also {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        MainActivity.start(this)
        finish()
    }

    override fun onResume() {
        super.onResume()
        viewModel.requestUser()
        /*Handler(Looper.getMainLooper()).postDelayed(
                {viewModel.requestUser()},
                1000
        )*/
    }
}