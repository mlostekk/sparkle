package com.nomad5.sparkle.model

import com.google.gson.annotations.SerializedName

/****************************************************************************************************************************
 * Model for categories
 *
 * Overriding the _id and _rev CouchDb names to fit camelCase
 */
data class Category(@SerializedName("_id") val id: String,
                    val type: String,
                    @SerializedName("_rev") val revision: String? = null)
