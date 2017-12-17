package com.nomad5.sparkle.ui.questions

import com.nomad5.sparkle.model.storage.StorageInterface
import java.lang.ref.WeakReference

/****************************************************************************************************************************
 */
class QuestionsPresenter(private val storage: StorageInterface) : QuestionsContract.Presenter {

    override var weakReference: WeakReference<QuestionsContract.View>? = null

    /****************************************************************************************************************************
     */
    override fun loadQuestions() {
        storage.loadCategories({ categories ->
            categories.forEach { category ->
                storage.loadQuestionsByCategory(category.type, { questions ->
                    view?.notifyQuestionsLoaded(category, questions)
                }, {
                    view?.notifyQuestionsLoadError("Error loading questions for '$category'")
                })
            }
        }, {
            view?.notifyQuestionsLoadError("Error loading categories")
        })
    }

}