package com.nickolay.kotlin_for_android.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.res.ResourcesCompat
import com.nickolay.kotlin_for_android.R
import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.ui.base.BaseActivity
import com.nickolay.kotlin_for_android.ui.dialogs.DeleteNoteDialog
import kotlinx.android.synthetic.main.activity_note.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : BaseActivity<NoteData>(){

    override val viewModel: NoteViewModel by viewModel()
    override val layoutRes = R.layout.activity_note
    private var note: Note? = null

    private var color: Note.Color = Note.Color.WHITE

    private val textWatcher = object : TextWatcher {
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
            toolbar.title =  getString(R.string.new_note_title)
            initView()
        }
    }

    override fun renderData(data: NoteData) {
        if (data.isDeleted) {
            finish()
        } else {
            this.note = data.note
            initView()
        }
    }

    private fun initView() {
        tietTitle.removeTextChangedListener(textWatcher)
        etBody.removeTextChangedListener(textWatcher)

        note?.let {
            tietTitle.setTextKeepState(it.title)
            etBody.setTextKeepState(it.text)
            toolbar.setBackgroundColor(ResourcesCompat.getColor(resources, it.color.id, null))
            toolbar.title = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(it.lastChanged)
            color = it.color
        }

        tietTitle.addTextChangedListener (textWatcher)
        etBody.addTextChangedListener (textWatcher)

        colorPicker.onColorClickListener = {
            color = it
            toolbar.setBackgroundColor(getColor(it.id))
            saveNote()
        }
    }

    private fun saveNote() {
        tietTitle.text?.let {
            if (it.length < 3)
                return
        } ?: return

        note = note?.copy(
            title = tietTitle.text.toString(),
            text = etBody.text.toString(),
            lastChanged = Date(),
            color = color
        ) ?: Note(
                title = tietTitle.text.toString(),
                text = etBody.text.toString(),
                color = color
        )

        note?.let {
            viewModel.save(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?) =
            menuInflater.inflate(R.menu.note_menu, menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        android.R.id.home -> {
                onBackPressed()
                true
            }
        R.id.mi_note_color -> togglePallete().let{ true }
        R.id.mi_note_delete -> deleteNote().let{ true}
        else -> super.onOptionsItemSelected(item)
    }

    private fun togglePallete(){
        if (colorPicker.isOpen) {
            colorPicker.close()
        } else {
            colorPicker.open()
        }
    }

    private fun deleteNote(){
            supportFragmentManager.findFragmentByTag(DeleteNoteDialog.TAG) ?: DeleteNoteDialog().show(supportFragmentManager, DeleteNoteDialog.TAG)
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