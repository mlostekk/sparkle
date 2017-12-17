package com.nomad5.sparkle

import android.support.v7.widget.LinearLayoutManager
import com.nomad5.sparkle.mock.app.TestSparkleApplication
import com.nomad5.sparkle.mock.model.storage.mock.MockStorage
import com.nomad5.sparkle.ui.questions.QuestionsActivity
import kotlinx.android.synthetic.main.activity_questions.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/****************************************************************************************************************************
 * The tests to verify the correct behavior of the view
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = TestSparkleApplication::class)
class QuestionsViewTest {

    lateinit private var mockStorage: MockStorage

    lateinit private var viewUnderTest: QuestionsActivity

    /****************************************************************************************************************************
     */
    @Before
    fun setup() {
        // init mock objects
        MockitoAnnotations.initMocks(this)
        // init model
        mockStorage = MockStorage()
    }

    /****************************************************************************************************************************
     */
    @Test
    fun testViewCreate() {
        /* Given & When */
        viewUnderTest = Robolectric.buildActivity(QuestionsActivity::class.java).create().get()
        /* Then */
        assertTrue(viewUnderTest.questions_list.layoutManager is LinearLayoutManager)
        assertNotNull(viewUnderTest.questions_list.adapter)
    }
}
