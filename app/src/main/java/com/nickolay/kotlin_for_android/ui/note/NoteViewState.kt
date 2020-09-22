package com.nickolay.kotlin_for_android.ui.note

import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.ui.base.BaseViewState

class NoteViewState(data: Data = Data(), error: Throwable? = null) : BaseViewState<NoteViewState.Data>(data, error) {
    data class Data(val isDeleted: Boolean = false, val note: Note? = null)
}
