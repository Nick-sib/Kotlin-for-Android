package com.nickolay.kotlin_for_android.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.nickolay.kotlin_for_android.R
import com.nickolay.kotlin_for_android.ui.base.BaseActivity
import com.nickolay.kotlin_for_android.ui.note.NoteViewModel

class DeleteNoteDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            AlertDialog.Builder(context!!)
                    .setTitle(R.string.dialog_delete_title)
                    .setMessage(getString(R.string.dialog_delete_message))
                    .setNegativeButton(getString(R.string.s_btn_cancel)) { _, _ -> dismiss() }
                    .setPositiveButton(getString(R.string.s_btn_ok)) { _, _ -> ((activity as BaseActivity<*, *>).viewModel as NoteViewModel).deleteNote() }
                    .create()

    companion object {
        val TAG = DeleteNoteDialog::class.java.name + "TAG"
        // fun createInstance() = LogoutDialog()
    }
}