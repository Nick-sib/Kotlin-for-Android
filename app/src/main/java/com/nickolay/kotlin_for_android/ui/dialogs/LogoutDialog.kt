package com.nickolay.kotlin_for_android.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.nickolay.kotlin_for_android.R

class LogoutDialog() : DialogFragment() {

    companion object {
        val TAG = LogoutDialog::class.java.name + "TAG"
        fun createInstance() = LogoutDialog()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            AlertDialog.Builder(context!!)
                    .setTitle(R.string.logout_dialog_title)
                    .setMessage(R.string.logout_dialog_message)
                    .setPositiveButton(R.string.ok_btn_title) { _, _ -> (activity as LogoutListener).onLogout() }
                    .setNegativeButton(R.string.logout_dialog_cancel) { _, _ -> dismiss() }
                    .create()

    interface LogoutListener {
        fun onLogout()
    }

}