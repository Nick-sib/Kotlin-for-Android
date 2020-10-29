package com.nickolay.kotlin_for_android.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.nickolay.kotlin_for_android.R

class LogoutDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            AlertDialog.Builder(context!!)
                    .setTitle(R.string.dialog_logout_title)
                    .setMessage(R.string.dialog_logout_message)
                    .setPositiveButton(R.string.s_btn_ok) { _, _ -> (activity as LogoutListener).onLogout() }
                    .setNegativeButton(R.string.s_btn_cancel) { _, _ -> dismiss() }
                    .create()

    interface LogoutListener {
        fun onLogout()
    }

    companion object {
        val TAG = LogoutDialog::class.java.name + "TAG"
    }
}