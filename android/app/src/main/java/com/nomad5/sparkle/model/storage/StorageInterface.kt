package com.nomad5.sparkle.model.storage

import com.nomad5.sparkle.model.Answer
import com.nomad5.sparkle.model.Category
import com.nomad5.sparkle.model.Question

/****************************************************************************************************************************
 * Interface definition for accessing the storage aka:
 *
 * - loading categories
 * - loading questions by category
 * - loading a specific question
 * - submitting answer
 *
 * The error handler return a simple error string here, some StatusCode would be better
 */
interface StorageInterface {

    /****************************************************************************************************************************
     * Load all categories
     */
    fun loadCategories(success: (List<Category>) -> Unit,
                       error: (String) -> Unit)

    /****************************************************************************************************************************
     * Load all questions for a particular category
     */
    fun loadQuestionsByCategory(category: String,
                                success: (List<Question>) -> Unit,
                                error: (String) -> Unit)

    /****************************************************************************************************************************
     * Load a particular question
     */
    fun loadQuestion(id: String,
                     success: (Question) -> Unit,
                     error: (String) -> Unit)

    /****************************************************************************************************************************
     * Submit an answer
     */
    fun submitAnswer(answer: Answer,
                     success: () -> Unit,
                     error: (String) -> Unit)
}