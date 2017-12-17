package com.nomad5.sparkle.ui.questions

import com.nomad5.sparkle.model.Category
import com.nomad5.sparkle.model.Question
import com.nomad5.sparkle.ui.base.BasePresenter
import com.nomad5.sparkle.ui.base.BaseView

/****************************************************************************************************************************
 * Contract between presenter and view
 */
interface QuestionsContract {

    interface Presenter : BasePresenter<View> {

        // trigger async loading of all questions
        fun loadQuestions()
    }

    interface View : BaseView {

        // called upon successful loading of all questions for one available category
        fun notifyQuestionsLoaded(category: Category, questions: List<Question>)

        // called upon loading error
        fun notifyQuestionsLoadError(error:String)

    }
}