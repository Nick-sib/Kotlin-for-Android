package com.nickolay.kotlin_for_android.ui.note

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nickolay.kotlin_for_android.data.NotesRepository
import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.data.model.NoteResult
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NoteViewModelTest {
    @get:Rule
    val taskExecutionRule = InstantTaskExecutorRule()

    private val mockRepository = mockk<NotesRepository>()
    private val noteLiveData = MutableLiveData<NoteResult>()
    private val testNote = Note("TESTNOTEID")
    private lateinit var viewModel: NoteViewModel

//    @Before
//    fun setup(){
//        clearAllMocks()
//        every { mockRepository.getNoteByID(testNote.id) } returns noteLiveData
//        every { mockRepository.deleteNote(testNote.id) } returns noteLiveData
//        viewModel = NoteViewModel(mockRepository)
//    }
//
//    @Test
//    fun `loadNote should return Note`(){
//        var result: NoteData.Data? = null
//        val testData = NoteData.Data(false, testNote)
//        viewModel.getViewState().observeForever {
//            result = it?.data
//        }
//        viewModel.loadNote(testNote.id)
//        noteLiveData.value = NoteResult.Success(testNote)
//        assertEquals(testData, result)
//    }
//
//
//    @Test
//    fun `loadNote should return error`() {
//        var result: Throwable? = null
//        val testData = Throwable("error")
//        viewModel.getViewState().observeForever {
//            result = it?.error
//        }
//        viewModel.loadNote(testNote.id)
//        noteLiveData.value = NoteResult.Error(testData)
//        assertEquals(testData, result)
//    }
//
//    @Test
//    fun `delete should return NoteData with isDeleted`() {
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
//    }
//
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