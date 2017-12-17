package com.nomad5.sparkle.injection

import com.nomad5.sparkle.ui.questions.QuestionsActivity
import dagger.Component
import javax.inject.Singleton

/****************************************************************************************************************************
 */
@Singleton
@Component(modules = [(StorageModule::class), (PresenterModule::class)])
interface AppComponent {

    fun inject(target: QuestionsActivity)

}