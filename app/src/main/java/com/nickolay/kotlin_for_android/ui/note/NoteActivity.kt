package com.nickolay.kotlin_for_android.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.nickolay.kotlin_for_android.R
import com.nickolay.kotlin_for_android.data.entity.Note
import kotlinx.android.synthetic.main.activity_note.*
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {
    companion object {

        const val DATE_TIME_FORMAT = "dd.MMM.yy HH:mm"
        private val EXTRA_NOTE = NoteActivity::class.java.name + "extra.NOTE"

        fun getStartIntent(context: Context, note: Note?): Intent {
            val intent = Intent(context, NoteActivity::class.java)
            intent.putExtra(EXTRA_NOTE, note)
            return intent
        }
    }

    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        note = intent.getParcelableExtra(EXTRA_NOTE)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = if (note != null) {
            SimpleDateFormat(DATE_TIME_FORMAT,
                    Locale.getDefault()).format(note!!.lastChanged)
        } else {
            getString(R.string.new_note_title)
        }

        initView()
    }

    private fun initView() {
        if (note != null) {
            tietTitle.setText(note?.title ?: "")
            etBody.setText(note?.text ?: "")
            toolbar.setBackgroundColor(ContextCompat.getColor(this, note!!.color.id))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }





}