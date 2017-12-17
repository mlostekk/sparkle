package com.nomad5.sparkle.injection

import com.nomad5.sparkle.model.storage.StorageInterface
import com.nomad5.sparkle.ui.questions.QuestionsContract
import com.nomad5.sparkle.ui.questions.QuestionsPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/****************************************************************************************************************************
 * Module which provides the presenters
 */
@Module
open class PresenterModule {

    @Provides
    @Singleton
    open fun provideQuestionsPresenter(storage: StorageInterface): QuestionsContract.Presenter {
        return QuestionsPresenter(storage)
    }

}