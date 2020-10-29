package com.nickolay.kotlin_for_android.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.firebase.ui.auth.AuthUI
import com.nickolay.kotlin_for_android.App
import com.nickolay.kotlin_for_android.R
import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.ui.adapter.NotesRVAdapter
import com.nickolay.kotlin_for_android.ui.base.BaseActivity
import com.nickolay.kotlin_for_android.ui.dialogs.LogoutDialog
import com.nickolay.kotlin_for_android.ui.note.NoteActivity
import com.nickolay.kotlin_for_android.ui.splash.SplashActivity

import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<List<Note>?>(), LogoutDialog.LogoutListener {

    override val viewModel: MainViewModel by viewModel()

    override val layoutRes = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTheme()
        rv_notes.adapter = NotesRVAdapter {
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

        if (App.instance.isDark) {
            menuItem.setIcon(R.drawable.ic_day_24)
        } else {
            menuItem.setIcon(R.drawable.ic_night_24)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean =
            MenuInflater(this).inflate(R.menu.main_menu, menu).let { true }

    fun onOptionsItemSelect(item: MenuItem) {
        when (item.itemId) {
            R.id.mi_theme -> {
                App.instance.isDark = !App.instance.isDark
            }
            R.id.mi_logout -> {
                showLogoutDialog()
            }
        }
    }

    private fun showLogoutDialog() {
        supportFragmentManager.findFragmentByTag(LogoutDialog.TAG)
                ?: LogoutDialog().show(supportFragmentManager, LogoutDialog.TAG)
    }

    override fun onLogout() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    startActivity(Intent(this, SplashActivity::class.java))
                    finish()
                }
    }

    companion object {
        fun start(context: Context) = Intent(context, MainActivity::class.java).apply {
            context.startActivity(this)
        }
    }

}