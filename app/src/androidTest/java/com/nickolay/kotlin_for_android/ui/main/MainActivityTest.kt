package com.nickolay.kotlin_for_android.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.nickolay.kotlin_for_android.R
import com.nickolay.kotlin_for_android.data.entity.Note
import com.nickolay.kotlin_for_android.data.model.NoteResult
import com.nickolay.kotlin_for_android.ui.adapter.NotesRVAdapter
import com.nickolay.kotlin_for_android.ui.note.NoteActivity
import com.nickolay.kotlin_for_android.ui.note.NoteViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.StandAloneContext.stopKoin

class MainActivityTest {

    @get:Rule
    val activityTestRule = IntentsTestRule(MainActivity::class.java, true, false)

    private val model: MainViewModel = mockk(relaxed = true)

    private val testNotes = listOf(
            Note("id1", "title1", "text1" ),
            Note("id2", "title2", "text2" ),
            Note("id3", "title3", "text3" ),
            Note("id4", "title4", "text4" ),
            Note("id5", "title5", "text5" )
    )

    private val channel = Channel<List<Note>>(Channel.CONFLATED)

    @Before
    fun setup() {
        loadKoinModules(
                listOf(
                        module {
                            viewModel { model }
                        }
                )
        )

        every { model.getViewState() } returns channel
        activityTestRule.launchActivity(null)
        runBlocking {
            channel.send(testNotes)
        }

        //viewStateLiveData.postValue(MainViewState(notes = testNotes))

    }

    @After
    fun teardown(){
        stopKoin()
    }

    @Test
    fun check_data_is_displayed(){
        onView(withText(testNotes[1].text)).check(matches(isDisplayed()))
//        onView(withId(R.id.rv_notes)).perform(scrollToPosition<NotesRVAdapter.ViewHolder>(1))
//        onView(withText(testNotes[1].text)).check(matches(isDisplayed()))
    }

}