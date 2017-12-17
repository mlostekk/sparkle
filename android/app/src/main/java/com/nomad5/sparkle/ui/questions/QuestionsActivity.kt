package com.nomad5.sparkle.ui.questions

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nomad5.sparkle.R
import com.nomad5.sparkle.app.SparkleApplication
import javax.inject.Inject

/****************************************************************************************************************************
 */
class QuestionsActivity : AppCompatActivity(), QuestionsContract.View {

    @Inject
    lateinit var presenter:QuestionsContract.Presenter

    /****************************************************************************************************************************
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        SparkleApplication.graph.inject(this)
    }
}
