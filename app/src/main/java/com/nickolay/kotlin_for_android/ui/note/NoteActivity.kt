package com.nickolay.kotlin_for_android.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.nickolay.kotlin_for_android.R
import com.nickolay.kotlin_for_android.data.entity.Note
import kotlinx.android.synthetic.main.activity_note.*
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {
    companion object {
        private const val DATE_TIME_FORMAT = "dd.MM.yy HH:mm"
        private val EXTRA_NOTE = NoteActivity::class.java.name + "extra.NOTE"

        fun start(context: Context, note: Note? = null): Intent = Intent(context, NoteActivity::class.java).apply {
            putExtra(EXTRA_NOTE, note)
            context.startActivity(this)
        }
    }

    private var note: Note? = null
    private lateinit var viewModel: NoteViewModel

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        note = intent.getParcelableExtra(EXTRA_NOTE)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = note?.let {
            SimpleDateFormat(DATE_TIME_FORMAT,
                    Locale.getDefault()).format(note!!.lastChanged)
        } ?: getString(R.string.new_note_title)


        initView()
    }

    private fun initView() {
        note?.let {
            tietTitle.setText(note?.title ?: "")
            etBody.setText(note?.text ?: "")
            toolbar.setBackgroundColor(ResourcesCompat.getColor(resources, note!!.color.id, null))
        }

        tietTitle.afterTextChanged { saveNote() }
        etBody.afterTextChanged { saveNote() }

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
                UUID.randomUUID().toString(),
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

}