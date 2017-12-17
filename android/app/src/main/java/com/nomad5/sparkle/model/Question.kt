package com.nomad5.sparkle.model

import com.google.gson.annotations.SerializedName

/****************************************************************************************************************************
 * Model for questions
 *
 * Overriding the _id and _rev CouchDb names to fit camelCase
 * Also overriding the serialized name for question_type to fit camelCase
 */
data class Question(@SerializedName("_id") val id: String,
                    val category: String,
                    val question: String,
                    @SerializedName("question_type") val questionType: Type,
                    @SerializedName("_rev") val revision: String? = null) {

    /****************************************************************************************************************************
     */
    data class Type(val type: String,
                    val options: List<String>,
                    val condition: TypeCondition? = null,
                    val range: TypeRange? = null)

    /****************************************************************************************************************************
     * For simplicity the predicate here is a simple map, could be done in a better way later
     * Overriding serialized name if_positive to match camelCase
     */
    data class TypeCondition(val predicate: Map<String, List<String>>,
                             @SerializedName("if_positive") val ifPositive: Question)

    /****************************************************************************************************************************
     */
    data class TypeRange(val from: Long,
                         val to: Long)
}