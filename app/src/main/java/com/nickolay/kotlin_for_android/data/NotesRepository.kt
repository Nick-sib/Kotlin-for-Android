package com.nickolay.kotlin_for_android.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.data.provider.DataProvider
import com.nickolay.kotlin_for_android.data.provider.FirestoreProvider
import java.util.*


object NotesRepository{

    private val dataProvider: DataProvider = FirestoreProvider()

    fun getNotes() = dataProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = dataProvider.saveNote(note)
    fun getNoteByID(id: String) = dataProvider.getNoteByID(id)



//    private val notes = mutableListOf(
//            Note(
//                    UUID.randomUUID().toString(),
//                    "Первая заметка",
//                    "Текст первой заметки. Не очень длинный, но интересный",
//                    Note.Color.BLUE
//            ),
//            Note(
//                    UUID.randomUUID().toString(),
//                    "Вторая заметка",
//                    "Текст второй заметки. Не очень длинный, но интересный",
//                    Note.Color.GREEN
//            ),
//            Note(
//                    UUID.randomUUID().toString(),
//                    "Третья заметка",
//                    "Текст третьей заметки. Не очень длинный, но интересный",
//                    Note.Color.PINK
//            ),
//            Note(
//                    UUID.randomUUID().toString(),
//                    "Четвертая заметка",
//                    "Текст четвертой заметки. Не очень длинный, но интересный",
//                    Note.Color.RED
//            ),
//            Note(
//                    UUID.randomUUID().toString(),
//                    "Пятая заметка",
//                    "Текст пятой заметки. Не очень длинный, но интересный",
//                    Note.Color.YELLOW
//            ),
//            Note(
//                    UUID.randomUUID().toString(),
//                    "Шестая заметка",
//                    "Текст шестой заметки. Не очень длинный, но интересный",
//                    Note.Color.VIOLET
//            ),
//            Note(
//                    UUID.randomUUID().toString(),
//                    "Первая заметка",
//                    "Текст первой заметки. Не очень длинный, но интересный",
//                    Note.Color.BLUE
//            ),
//            Note(
//                    UUID.randomUUID().toString(),
//                    "Вторая заметка",
//                    "Текст второй заметки. Не очень длинный, но интересный",
//                    Note.Color.GREEN
//            ),
//            Note(
//                    UUID.randomUUID().toString(),
//                    "Третья заметка",
//                    "Текст третьей заметки. Не очень длинный, но интересный",
//                    Note.Color.PINK
//            ),
//            Note(
//                    UUID.randomUUID().toString(),
//                    "Четвертая заметка",
//                    "Текст четвертой заметки. Не очень длинный, но интересный",
//                    Note.Color.RED
//            ),
//            Note(
//                    UUID.randomUUID().toString(),
//                    "Пятая заметка",
//                    "Текст пятой заметки. Не очень длинный, но интересный",
//                    Note.Color.YELLOW
//            ),
//            Note(
//                    UUID.randomUUID().toString(),
//                    "Шестая заметка",
//                    "Текст шестой заметки. Не очень длинный, но интересный",
//                    Note.Color.VIOLET
//            ),
//            Note(
//                    UUID.randomUUID().toString(),
//                    "Первая заметка",
//                    "Текст первой заметки. Не очень длинный, но интересный",
//                    Note.Color.BLUE
//            ),
//            Note(
//                    UUID.randomUUID().toString(),
//                    "Вторая заметка",
//                    "Текст второй заметки. Не очень длинный, но интересный",
//                    Note.Color.GREEN
//            ),
//            Note(
//                    UUID.randomUUID().toString(),
//                    "Третья заметка",
//                    "Текст третьей заметки. Не очень длинный, но интересный",
//                    Note.Color.PINK
//            ),
//            Note(
//                    UUID.randomUUID().toString(),
//                    "Четвертая заметка",
//                    "Текст четвертой заметки. Не очень длинный, но интересный",
//                    Note.Color.RED
//            ),
//            Note(
//                    UUID.randomUUID().toString(),
//                    "Пятая заметка",
//                    "Текст пятой заметки. Не очень длинный, но интересный",
//                    Note.Color.YELLOW
//            ),
//            Note(
//                    UUID.randomUUID().toString(),
//                    "Шестая заметка",
//                    "Текст шестой заметки. Не очень длинный, но интересный",
//                    Note.Color.VIOLET
//            )
//
//
//    )
//
//    private val mNotesLiveData = MutableLiveData<List<Note>>().apply {
//        value = notes
//    }
//
//    val notesLiveData: LiveData<List<Note>> = mNotesLiveData
//
//    fun saveNote(note: Note){
//        addOrReplase(note)
//        mNotesLiveData.value = notes
//    }
//
//    private fun addOrReplase(note: Note){
//        for (i in 0 until notes.size){
//            if (notes[i] == note) {
//                notes[i] = note
//                return
//            }
//        }
//
//        notes.add(note)
//    }
}