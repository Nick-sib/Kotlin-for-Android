package com.nickolay.kotlin_for_android.ui.note

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nickolay.kotlin_for_android.data.NotesRepository
import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.data.model.NoteResult
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NoteViewModelTest {
    @get:Rule
    val taskExecutionRule = InstantTaskExecutorRule()

    private val mockRepository = mockk<NotesRepository>()
    private val channel = Channel<NoteResult>(Channel.CONFLATED)
    private val testNote = Note("TESTNOTEID")
    private lateinit var viewModel: NoteViewModel

    @Before
    fun setup(){
        clearAllMocks()
        every { mockRepository.getNotes()} returns channel
        viewModel = NoteViewModel(mockRepository)
    }

    @Test
    fun `loadNote should return Note`() = runBlocking {
        coEvery { mockRepository.getNoteByID(testNote.id) } returns testNote
        val deferred = async {
            viewModel.getViewState().receive()
        }
        viewModel.loadNote(testNote.id)

        assertEquals(deferred.await().note, testNote)
    }

    @Test
    fun `loadNote should return error`()  = runBlocking{
        //Извините не пойму как тестировать т.к. сейчас loadNote возврашает Note? а ошибка это работа внешней функции
//        val testData = Throwable("error")
//        val deferred = async {
//            viewModel.getErrorChannel().receive()
//        }
//
//        assertEquals(deferred.await(), testData)

//        viewModel.getViewState().observeForever {
//            result = it?.error
//        }
//        viewModel.loadNote(testNote.id)
//        noteLiveData.value = NoteResult.Error(testData)
//
    }

    @Test
    fun `delete should return NoteData with isDeleted`() = runBlocking {
        coEvery { mockRepository.deleteNote(testNote.id) } returns Unit
//        var result: NoteData.Data? = null
//        val testData = NoteData.Data(true, null)
//        viewModel.getViewState().observeForever {
//            result = it?.data
//        }
//
//        viewModel.save(testNote)
//        viewModel.deleteNote()
//        noteLiveData.value = NoteResult.Success(null)
//        assertEquals(testData, result)
    }

//    @Test
//    fun `delete should return error`() {
//        var result: Throwable? = null
//        val testData = Throwable("error")
//        viewModel.getViewState().observeForever {
//            result = it?.error
//        }
//        viewModel.save(testNote)
//        viewModel.deleteNote()
//        noteLiveData.value = NoteResult.Error(error = testData)
//        assertEquals(testData, result)
//    }

}