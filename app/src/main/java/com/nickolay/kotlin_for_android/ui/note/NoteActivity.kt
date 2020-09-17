package com.nickolay.kotlin_for_android.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.EditText
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.nickolay.kotlin_for_android.R
import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_note.*
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : BaseActivity<Note?, NoteViewState>(){

    override val viewModel by lazy {
        ViewModelProvider(this).get(NoteViewModel::class.java)
    }
    override val layoutRes = R.layout.activity_note

    private var note: Note? = null

    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            saveNote()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val noteID = intent.getStringExtra(EXTRA_NOTE)

        noteID?.let {
            viewModel.loadNote(it)
        } ?: let {
            supportActionBar?.title =  getString(R.string.new_note_title)
            initView()
        }
    }

    override fun renderData(data: Note?) {
        this.note = data
        supportActionBar?.title = note?.let {
            SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(it.lastChanged)
        } ?: getString(R.string.new_note_title)
        initView()
    }

    private fun initView() {

        tietTitle.removeTextChangedListener(textWatcher)
        etBody.removeTextChangedListener(textWatcher)

        note?.let {
            tietTitle.setTextKeepState(it.title)
            etBody.setTextKeepState(it.text)
            toolbar.setBackgroundColor(ResourcesCompat.getColor(resources, it.color.id, null))
        }

        tietTitle.addTextChangedListener (textWatcher)
        etBody.addTextChangedListener (textWatcher)
    }


    private fun saveNote() {

        tietTitle.text?.let {
            if (it.length < 3)
                return
        } ?: return

        note = note?.copy(
            title = tietTitle.text.toString(),
            text = etBody.text.toString(),
            lastChanged = Date()
        ) ?: Note(
                title = tietTitle.text.toString(),
                text = etBody.text.toString()
        )

        note?.let {
            viewModel.saveChanges(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }


    companion object {
        private const val DATE_TIME_FORMAT = "dd.MM.yy HH:mm"
        private val EXTRA_NOTE = NoteActivity::class.java.name + "extra.NOTE"

        fun start(context: Context, noteID: String? = null): Intent = Intent(context, NoteActivity::class.java).apply {
            putExtra(EXTRA_NOTE, noteID)
            context.startActivity(this)
        }
    }
}