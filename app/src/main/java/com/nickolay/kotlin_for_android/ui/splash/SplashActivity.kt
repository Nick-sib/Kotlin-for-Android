package com.nickolay.kotlin_for_android.ui.splash


import com.nickolay.kotlin_for_android.ui.base.BaseActivity
import com.nickolay.kotlin_for_android.ui.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity: BaseActivity<Boolean?, SplashViewState>() {

    override val viewModel: SplashViewModel by viewModel()

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