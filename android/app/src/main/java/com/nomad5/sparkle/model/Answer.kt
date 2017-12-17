package com.nomad5.sparkle.model

import com.google.gson.annotations.SerializedName

/****************************************************************************************************************************
 * The model for the answer for a specific question.
 *
 * Overriding the _id and _rev CouchDb names to fit camelCase
 */
data class Answer(@SerializedName("_id") val id: String,
                  val questionId: String,
                  val answer: String,
                  @SerializedName("_rev") val revision: String? = null)