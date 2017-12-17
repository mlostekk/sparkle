package com.nomad5.sparkle

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nomad5.sparkle.mock.model.storage.mock.MockStorage
import com.nomad5.sparkle.model.Category
import com.nomad5.sparkle.model.Question
import com.nomad5.sparkle.ui.questions.QuestionsContract
import com.nomad5.sparkle.ui.questions.QuestionsPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/****************************************************************************************************************************
 * The tests to verify the correct behavior of the view
 */
class QuestionsPresenterTest {

    @Mock
    private lateinit var view: QuestionsContract.View

    private lateinit var mockStorage: MockStorage
    private lateinit var presenterUnderTest: QuestionsPresenter

    /****************************************************************************************************************************
     * Initial setup
     */
    @Before
    fun setup() {
        // init mock objects
        MockitoAnnotations.initMocks(this)
        // init model
        mockStorage = MockStorage()
        /* Global given */
        presenterUnderTest = QuestionsPresenter(mockStorage)
        presenterUnderTest.attachView(view)
        assertTrue(presenterUnderTest.view == view)
    }

    /****************************************************************************************************************************
     */
    @Test
    fun testLoadQuestionsSuccessFully() {
        /* When */
        MockStorage.connectionSuccessful = true
        presenterUnderTest.loadQuestions()
        /* Then */
        val distinctQuestions = MockStorage.questions.distinctBy { it.category }.size
        val argCategoryCaptor = argumentCaptor<Category>()
        val argQuestionsCaptor = argumentCaptor<List<Question>>()
        verify(view, times(distinctQuestions)).notifyQuestionsLoaded(argCategoryCaptor.capture(), argQuestionsCaptor.capture())
        assertEquals(argCategoryCaptor.allValues.size, argQuestionsCaptor.allValues.size)
        for (index in 0 until argCategoryCaptor.allValues.size) {
            val category = argCategoryCaptor.allValues[index].type
            assertTrue(argQuestionsCaptor.allValues[index].all { it.category == category })
        }
    }

    /****************************************************************************************************************************
     */
    @Test
    fun testLoadQuestionsSuccessError() {
        /* When */
        MockStorage.connectionSuccessful = false
        presenterUnderTest.loadQuestions()
        /* Then */
        verify(view, times(1)).notifyQuestionsLoadError(any())
    }
}
