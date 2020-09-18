package com.nickolay.kotlin_for_android.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.auth.AuthUI
import com.nickolay.kotlin_for_android.R
import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.ui.adapter.NotesRVAdapter
import com.nickolay.kotlin_for_android.ui.base.BaseActivity
import com.nickolay.kotlin_for_android.ui.dialogs.LogoutDialog
import com.nickolay.kotlin_for_android.ui.note.NoteActivity
import com.nickolay.kotlin_for_android.ui.splash.SplashActivity

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<List<Note>?, MainViewState>(), LogoutDialog.LogoutListener {

    private var isDark = false

    private fun saveKey(theme: Int) = sharedPrefs.edit().putInt(PREFS_KEY_THEME, theme).apply()
    private fun loadKey() = sharedPrefs.getInt(PREFS_KEY_THEME, 0)

    private val sharedPrefs by lazy {
        getSharedPreferences(
                (MainActivity::class).qualifiedName,
                Context.MODE_PRIVATE
        )
    }

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    override val layoutRes = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTheme()

        rv_notes.adapter = NotesRVAdapter{
            NoteActivity.start(this, it.id)
        }

        fab.setOnClickListener {
            NoteActivity.start(this)
        }
    }

    override fun renderData(data: List<Note>?) {
        data?.let {
            (rv_notes.adapter as NotesRVAdapter).notes = it
        }
    }

    private fun initTheme() {
        val menuItem = bottomAppBar.menu.findItem(R.id.mi_theme)
        isDark = loadKey() == THEME_DARK
        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            menuItem.setIcon(R.drawable.ic_day_24)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            menuItem.setIcon(R.drawable.ic_night_24)
        }
    }

    private fun setTheme(themeMode: Int, prefsMode: Int) {
        saveKey(prefsMode)
        AppCompatDelegate.setDefaultNightMode(themeMode)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean =
            MenuInflater(this).inflate(R.menu.main_menu, menu).let { true }



    fun onOptionsItemSelect(item: MenuItem) {
        when (item.itemId){
            R.id.mi_theme -> {
                    if (isDark){
                        setTheme(AppCompatDelegate.MODE_NIGHT_NO, THEME_LIGHT)
                    } else {
                        setTheme(AppCompatDelegate.MODE_NIGHT_YES, THEME_DARK)
                    }
                }
            R.id.mi_logout -> {
                showLogoutDialog()
            }
        }
    }

    fun showLogoutDialog() {
        supportFragmentManager.findFragmentByTag(LogoutDialog.TAG) ?: LogoutDialog().show(supportFragmentManager, LogoutDialog.TAG)
    }

    override fun onLogout() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    startActivity(Intent(this, SplashActivity::class.java))
                    finish()
                }
    }


    companion object{
        private const val PREFS_KEY_THEME = "theme"
        private const val THEME_LIGHT = 0
        private const val THEME_DARK = 1


        fun start(context: Context) = Intent(context, MainActivity::class.java).apply {
            context.startActivity(this)
        }
    }



}