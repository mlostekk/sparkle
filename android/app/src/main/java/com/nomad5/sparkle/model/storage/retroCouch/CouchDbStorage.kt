package com.nomad5.sparkle.model.storage.retroCouch

import com.google.gson.Gson
import com.nomad5.sparkle.model.Answer
import com.nomad5.sparkle.model.Category
import com.nomad5.sparkle.model.Question
import com.nomad5.sparkle.model.storage.StorageInterface
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/****************************************************************************************************************************
 * Inline helper to remove response handling redundancy
 */
inline fun <reified T : Any> processResponse(response: Response<T>?,
                                             error: (String) -> Unit,
                                             success: (T) -> Unit) {
    if (response == null) {
        error("Response was null")
    } else if (response.isSuccessful && response.errorBody() == null && response.body() != null) {
        success(response.body()!!)
    } else if (response.errorBody() != null) {
        error(response.errorBody()!!.string())
    } else {
        error("Empty error body received")
    }
}

/****************************************************************************************************************************
 * This is the Retrofit & CouchDb implementation
 */
@Singleton
class CouchDbStorage(gson: Gson) : StorageInterface {

    /****************************************************************************************************************************
     */
    companion object {
        val baseUrl = "http://192.168.178.22:5984"
        val defaultError = "Unknown error"
    }

    /****************************************************************************************************************************
     */
    private val api: CouchDbApi by lazy {
        Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(CouchDbApi::class.java)
    }

    /****************************************************************************************************************************
     */
    override fun loadCategories(success: (List<Category>) -> Unit, error: (String) -> Unit) {
        api.fetchCategories().enqueue(object : Callback<CouchDb.Response<CouchDb.AllDocsRow<Category>>> {
            override fun onResponse(call: Call<CouchDb.Response<CouchDb.AllDocsRow<Category>>>?, response: Response<CouchDb.Response<CouchDb.AllDocsRow<Category>>>?) {
                processResponse(response, error, { body ->
                    success(body.rows.map { it.doc })
                })
            }

            override fun onFailure(call: Call<CouchDb.Response<CouchDb.AllDocsRow<Category>>>?, t: Throwable?) {
                error(t?.localizedMessage ?: defaultError)
            }
        })
    }

    /****************************************************************************************************************************
     * We need this special query encoding here as CouchDb needs the double quotes
     * (\"$category\")
     */
    override fun loadQuestionsByCategory(category: String, success: (List<Question>) -> Unit, error: (String) -> Unit) {
        api.fetchQuestionsByCategory("\"$category\"").enqueue(object : Callback<CouchDb.Response<CouchDb.ViewRow<Question>>> {
            override fun onResponse(call: Call<CouchDb.Response<CouchDb.ViewRow<Question>>>?, response: Response<CouchDb.Response<CouchDb.ViewRow<Question>>>?) {
                processResponse(response, error, { body ->
                    success(body.rows.map { it.value })
                })
            }

            override fun onFailure(call: Call<CouchDb.Response<CouchDb.ViewRow<Question>>>?, t: Throwable?) {
                error(t?.localizedMessage ?: defaultError)
            }
        })
    }

    /****************************************************************************************************************************
     */
    override fun loadQuestion(id: String, success: (Question) -> Unit, error: (String) -> Unit) {
        api.fetchQuestion(id).enqueue(object : Callback<Question> {
            override fun onResponse(call: Call<Question>?, response: Response<Question>?) {
                processResponse(response, error, { body ->
                    success(body)
                })
            }

            override fun onFailure(call: Call<Question>?, t: Throwable?) {
                error(t?.localizedMessage ?: defaultError)
            }
        })
    }

    /****************************************************************************************************************************
     */
    override fun submitAnswer(answer: Answer, success: () -> Unit, error: (String) -> Unit) {
        api.putAnswer(answer, answer.id).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                processResponse(response, error, {
                    success()
                })
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                error(t?.localizedMessage ?: defaultError)
            }
        })
    }
}