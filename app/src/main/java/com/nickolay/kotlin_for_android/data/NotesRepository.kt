package com.nickolay.kotlin_for_android.data

import com.nickolay.kotlin_for_android.data.entity.Note
import java.util.*


object NotesRepository{
    var notes: List<Note> = listOf(
            Note(
                    UUID.randomUUID().toString(),
                    "Первая заметка",
                    "Текст первой заметки. Не очень длинный, но интересный",
                    Note.Color.BLUE
            ),
            Note(
                    UUID.randomUUID().toString(),
                    "Вторая заметка",
                    "Текст второй заметки. Не очень длинный, но интересный",
                    Note.Color.GREEN
            ),
            Note(
                    UUID.randomUUID().toString(),
                    "Третья заметка",
                    "Текст третьей заметки. Не очень длинный, но интересный",
                    Note.Color.PINK
            ),
            Note(
                    UUID.randomUUID().toString(),
                    "Четвертая заметка",
                    "Текст четвертой заметки. Не очень длинный, но интересный",
                    Note.Color.RED
            ),
            Note(
                    UUID.randomUUID().toString(),
                    "Пятая заметка",
                    "Текст пятой заметки. Не очень длинный, но интересный",
                    Note.Color.YELLOW
            ),
            Note(
                    UUID.randomUUID().toString(),
                    "Шестая заметка",
                    "Текст шестой заметки. Не очень длинный, но интересный",
                    Note.Color.VIOLET
            )
    )
    private set


}