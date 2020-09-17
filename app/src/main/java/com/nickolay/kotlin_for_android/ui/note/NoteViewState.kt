package com.nickolay.kotlin_for_android.ui.note

import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.ui.base.BaseViewState

class NoteViewState(note : Note? = null, error: Throwable? = null): BaseViewState<Note?>(note, error)
