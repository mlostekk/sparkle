package com.nomad5.sparkle.mock.model.storage.mock

import com.nomad5.sparkle.model.Answer
import com.nomad5.sparkle.model.Category
import com.nomad5.sparkle.model.Question
import com.nomad5.sparkle.model.storage.StorageInterface
import javax.inject.Singleton

/****************************************************************************************************************************
 * This is a simple memory implementation of the storage for testing purpose
 */
@Singleton
class MockStorage : StorageInterface {

    /****************************************************************************************************************************
     * Static data
     */
    companion object {
        var connectionSuccessful = true

        val categories = listOf(
                Category("id1", "cat1", "rev1"),
                Category("id2", "cat2", "rev2"))
        val questions = listOf(
                Question("id1", "cat1", "question1", Question.Type("type1", listOf())),
                Question("id2", "cat1", "question2", Question.Type("type2", listOf())),
                Question("id3", "cat2", "question3", Question.Type("type3", listOf())))
        val answers = mutableListOf<Answer>()
    }

    /****************************************************************************************************************************
     * Reset statics
     */
    init {
        connectionSuccessful = true
        answers.clear()
    }

    /****************************************************************************************************************************
     */
    override fun loadCategories(success: (List<Category>) -> Unit, error: (String) -> Unit) {
        if (connectionSuccessful) {
            success(categories)
        } else {
            error("Error")
        }
    }

    /****************************************************************************************************************************
     */
    override fun loadQuestionsByCategory(category: String, success: (List<Question>) -> Unit, error: (String) -> Unit) {
        if (connectionSuccessful) {
            success(questions.filter { it.category == category })
        } else {
            error("Error")
        }
    }

    /****************************************************************************************************************************
     */
    override fun loadQuestion(id: String, success: (Question) -> Unit, error: (String) -> Unit) {
        if (connectionSuccessful) {
            questions.firstOrNull { it.id == id }?.let {
                success(it)
                return
            }
        }
        error("Error")
    }

    /****************************************************************************************************************************
     */
    override fun submitAnswer(answer: Answer, success: () -> Unit, error: (String) -> Unit) {
        throw NotImplementedError()
    }

}