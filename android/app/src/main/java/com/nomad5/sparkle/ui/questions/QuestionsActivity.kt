package com.nomad5.sparkle.ui.questions

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nomad5.sparkle.R
import com.nomad5.sparkle.app.SparkleApplication
import com.nomad5.sparkle.model.Category
import com.nomad5.sparkle.model.Question
import kotlinx.android.synthetic.main.activity_questions.*
import kotlinx.android.synthetic.main.activity_questions_item.view.*
import javax.inject.Inject

/****************************************************************************************************************************
 */
class QuestionsActivity : AppCompatActivity(), QuestionsContract.View {

    @Inject
    lateinit var presenter: QuestionsContract.Presenter

    /****************************************************************************************************************************
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        // inject
        SparkleApplication.graph.inject(this)
        // setup action bar and questions list
        actionBar?.setDisplayHomeAsUpEnabled(false)
        questions_list?.setHasFixedSize(true)
        questions_list?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        questions_list?.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        questions_list?.adapter = QuestionsAdapter()
    }

    /****************************************************************************************************************************
     */
    override fun onResume() {
        super.onResume()
        questions_loading_overlay?.animate()?.alpha(1F)?.start()
        (questions_list?.adapter as? QuestionsAdapter)?.clear()
        presenter.attachView(this)
        presenter.loadQuestions()
    }

    /****************************************************************************************************************************
     */
    override fun onPause() {
        presenter.detachView()
        super.onPause()
    }

    /****************************************************************************************************************************
     */
    override fun notifyQuestionsLoaded(category: Category, questions: List<Question>) {
        (questions_list?.adapter as? QuestionsAdapter)?.addQuestions(category, questions)
        questions_loading_overlay?.animate()?.alpha(0F)?.start()
    }

    /****************************************************************************************************************************
     */
    override fun notifyQuestionsLoadError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    /****************************************************************************************************************************
     */
    class QuestionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    /****************************************************************************************************************************
     */
    inner class QuestionsAdapter : RecyclerView.Adapter<QuestionsViewHolder>() {

        val questions: MutableList<Question> = mutableListOf()

        fun clear() {
            questions.clear()
            notifyDataSetChanged()
        }

        fun addQuestions(category: Category, newQuestions: List<Question>) {
            questions.addAll(newQuestions)
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int = questions.size

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): QuestionsViewHolder {
            val inflater = LayoutInflater.from(parent?.context)
            val view = inflater.inflate(R.layout.activity_questions_item, parent, false)
            return QuestionsViewHolder(view)
        }

        override fun onBindViewHolder(holder: QuestionsViewHolder?, position: Int) {
            val question = questions[position]
            holder?.itemView?.questions_item_id?.text = question.question
            holder?.itemView?.setOnClickListener { /* TODO call detail activity */ }
        }
    }


}
