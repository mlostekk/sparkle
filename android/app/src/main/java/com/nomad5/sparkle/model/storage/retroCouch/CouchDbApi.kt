package com.nomad5.sparkle.model.storage.retroCouch

import com.nomad5.sparkle.model.Answer
import com.nomad5.sparkle.model.Category
import com.nomad5.sparkle.model.Question
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/****************************************************************************************************************************
 * The Retrofit api definition to access the CouchDb rest interface
 */
interface CouchDbApi {

    @GET("categories/_all_docs")
    fun fetchCategories(@Query("include_docs") includeDocs: Boolean = true): Call<CouchDb.Response<CouchDb.AllDocsRow<Category>>>

    @GET("questions/_design/questions/_view/by_category")
    fun fetchQuestionsByCategory(@Query("key") category: String): Call<CouchDb.Response<CouchDb.ViewRow<Question>>>

    @GET("questions/{questionId}")
    fun fetchQuestion(@Path("questionId") id: String): Call<Question>

    @PUT("answers/{id}")
    @Headers("Content-Type: application/json")
    fun putAnswer(@Body answer: Answer, @Path("id") id: String): Call<ResponseBody>
}