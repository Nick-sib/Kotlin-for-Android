package com.nickolay.kotlin_for_android.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nickolay.kotlin_for_android.data.NotesRepository
import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.data.model.NoteResult
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest{

    @get:Rule
    val taskExecutionRule = InstantTaskExecutorRule()


    private val mockRepository = mockk<NotesRepository>()
    private val notesLiveData = MutableLiveData<NoteResult>()
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup(){
        clearAllMocks()
        every { mockRepository.getNotes() } returns notesLiveData
        viewModel = MainViewModel(mockRepository)
    }

    @Test
    fun `should call getNotes once`() {
        verify(exactly = 1) { mockRepository.getNotes() }
    }

    @Test
    fun `should return Notes`() {
        var result: MainViewState? = null
        val testData = listOf(Note(), Note())

        viewModel.getViewState().observeForever{
            result = it
        }
        notesLiveData.value = NoteResult.Success(testData)

        assertEquals(result?.data, testData)
        assertEquals(result?.error, null)
    }

    @Test
    fun `should return error`() {
        var result: MainViewState? = null
        val testData = Throwable("error")

        viewModel.getViewState().observeForever{
            result = it
        }
        notesLiveData.value = NoteResult.Error(testData)

        assertEquals(result?.error, testData)
        assertEquals(result?.notes, null)

    }

    @Test
    fun `should remove observer`(){
        viewModel.onCleared()

        assertFalse(notesLiveData.hasObservers())
    }

}