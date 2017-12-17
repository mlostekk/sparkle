package com.nomad5.sparkle.model.storage.retroCouch

import com.google.gson.annotations.SerializedName

/****************************************************************************************************************************
 * The models needed for the CouchDb / Retrofit implementation of the StorageInterface
 */
interface CouchDb {

    /****************************************************************************************************************************
     * Special model row class for the model response coming from CouchDB via a design/view
     */
    data class ViewRow<out T>(val id: String,
                              val key: String,
                              val value: T)

    /****************************************************************************************************************************
     * Special model row class for the response coming from CouchDB via _all_docs
     */
    data class AllDocsRow<out T>(val id: String,
                                 val key: String,
                                 val doc: T)

    /****************************************************************************************************************************
     * Special model class for the response coming from CouchDB
     */
    data class Response<out T>(@SerializedName("total_rows") val totalRows: Long,
                               val offset: Long,
                               val rows: List<T>)
}