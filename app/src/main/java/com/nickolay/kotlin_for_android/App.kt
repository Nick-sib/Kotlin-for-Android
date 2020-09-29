package com.nickolay.kotlin_for_android

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.nickolay.kotlin_for_android.di.appModule
import com.nickolay.kotlin_for_android.di.mainModule
import com.nickolay.kotlin_for_android.di.noteModule
import com.nickolay.kotlin_for_android.di.splashModule
import com.nickolay.kotlin_for_android.ui.main.MainActivity
import org.koin.android.ext.android.startKoin

class App: Application() {

    companion object {
        const val PREFS_KEY_THEME = "theme"
        const val THEME_LIGHT = 0
        const val THEME_DARK = 1

        lateinit var instance : App
            private set

    }

    private fun saveKey(theme: Int) = sharedPrefs.edit().putInt(App.PREFS_KEY_THEME, theme).apply()

    private fun loadKey() = sharedPrefs.getInt(PREFS_KEY_THEME, 0)

    var isDark = false
        set(value) {
            if (field != value) {
                field = value
                AppCompatDelegate.setDefaultNightMode(
                        if (value) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
                saveKey(if (value) THEME_DARK else THEME_LIGHT)
            }
        }

    private val sharedPrefs by lazy {
        getSharedPreferences(
                (MainActivity::class).qualifiedName,
                Context.MODE_PRIVATE
        )
    }

    override fun onCreate() {
        super.onCreate()

        isDark = loadKey() == THEME_DARK

        instance = this
        startKoin(this, listOf(appModule, splashModule, mainModule, noteModule))

    }

}