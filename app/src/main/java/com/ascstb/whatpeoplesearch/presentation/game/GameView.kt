package com.ascstb.whatpeoplesearch.presentation.game

import android.os.Bundle
import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ascstb.whatpeoplesearch.databinding.GameLayoutBinding
import com.ascstb.whatpeoplesearch.model.Category
import com.ascstb.whatpeoplesearch.core.Game
import kotlinx.android.synthetic.main.game_layout.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.*
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import com.ascstb.whatpeoplesearch.R


class GameView : Fragment() {
    private val viewModel by viewModel<AnswerViewModel>()
    private lateinit var layout: View
    private lateinit var adapter: RVAnswerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return GameLayoutBinding.inflate(inflater, container, false).root.also { view ->
            this.layout = view
            setPlayer()
            generateRandomQuestion()
            setCurrentQuestion()
            fixCurrentQuestion()
            setOnSearchListener()
            initRecyclerView()

            getApiInfo()

            viewModel.updateAnswer.observe(this, Observer { correctGuess ->
                setCurrentQuestion()

                //region Player
                if (!correctGuess) Game.turn++
                setPlayer()
                //endregion

                adapter.updateAnswers(Game.answers)
            })
        }
    }

    private fun getApiInfo() {
        viewModel.updateParameters(
            language = Locale.getDefault().language,
            query = viewModel.currentQuestion
        )

        viewModel.answersList.observe(this, Observer { answers ->
            answers.forEach {
                Timber.d("GameView_TAG: getApiInfo: answer: ${it.answer}")
            }
            Game.answers = answers
            adapter.updateAnswers(Game.answers)
        })
    }

    private fun initRecyclerView() {
        adapter = RVAnswerAdapter(
            answerList = mutableListOf()
        )
        layout.rvAnswers.layoutManager = LinearLayoutManager(context)
        layout.rvAnswers.adapter = adapter
    }

    private fun setPlayer() {
        layout.currentPlayer.text = Game.printCurrentPlayer()
    }

    //region Questions
    private fun setOnSearchListener() {
        layout.search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val guess = layout.search.text.toString().trim()
                if (guess.isEmpty()) return@setOnEditorActionListener false

                viewModel.guess = guess
                viewModel.tryGuessing()

                context?.let { ctx ->
                    val inputManager = ctx.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager.hideSoftInputFromWindow(
                        this.parentFragment?.activity?.currentFocus?.windowToken,
                        HIDE_NOT_ALWAYS
                    )
                }
                return@setOnEditorActionListener true
            }

            return@setOnEditorActionListener false
        }
    }

    private fun fixCurrentQuestion() {
        layout.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (!s.toString().startsWith(viewModel.currentQuestion)) {
                    layout.search.setText(viewModel.currentQuestion)
                    Selection.setSelection(layout.search.text, layout.search.length())
                }
            }
        })
    }

    private fun setCurrentQuestion() {
        layout.search.setText(viewModel.currentQuestion)
        Selection.setSelection(layout.search.text, viewModel.currentQuestion.length)
    }

    private fun generateRandomQuestion() {
        viewModel.questions = getQuestions().toList()
        viewModel.currentQuestion = viewModel.questions.random() + " "
    }

    private fun getQuestions() = when (Game.currentCategory) {
        Category.CULTURE -> resources.getStringArray(R.array.culture)
        Category.PEOPLE -> resources.getStringArray(R.array.people)
        Category.NAMES -> resources.getStringArray(R.array.names)
        Category.QUESTIONS -> resources.getStringArray(R.array.questions)
        else -> emptyArray<String>()
    }
    //endregion
}
