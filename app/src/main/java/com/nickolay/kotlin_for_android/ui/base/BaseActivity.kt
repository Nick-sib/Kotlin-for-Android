package com.nickolay.kotlin_for_android.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

abstract class BaseActivity<T, S: BaseViewState<T>>: AppCompatActivity() {
    abstract val viewModel: BaseViewModel<T, S>
    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)

        viewModel.viewState.observe(this, Observer {state ->
            state ?: return@Observer
            state.error?.let {
                renderError(it)
                return@Observer
            }

            renderData(state.data)
        })

    }


    abstract fun renderData(data: T)

    protected fun renderError(error: Throwable){
        error.message?.let {
            showError(it)
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}