package com.nomad5.sparkle.mock.injection

import com.nomad5.sparkle.injection.PresenterModule
import com.nomad5.sparkle.mock.model.storage.mock.MockStorage
import com.nomad5.sparkle.model.storage.StorageInterface
import com.nomad5.sparkle.ui.questions.QuestionsContract
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import javax.inject.Singleton

/****************************************************************************************************************************
 * Module which provides mocked storage related objects, works only with mock storage
 */
@Module
class TestPresenterModule : PresenterModule() {

    @Provides
    @Singleton
    override fun provideQuestionsPresenter(storage: StorageInterface): QuestionsContract.Presenter {
        val mock = mock(QuestionsContract.Presenter::class.java)
        `when`(mock.loadQuestions()).then {
            if (MockStorage.connectionSuccessful) {
                MockStorage.categories.forEach { category ->
                    mock.view?.notifyQuestionsLoaded(category, MockStorage.questions.filter { it.category == category.type })
                }
            } else {
                mock.view?.notifyQuestionsLoadError("Error")
            }
        }
        return mock
    }
}