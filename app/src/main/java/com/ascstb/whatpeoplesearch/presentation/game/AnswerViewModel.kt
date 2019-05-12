package com.ascstb.whatpeoplesearch.presentation.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ascstb.whatpeoplesearch.core.Game
import com.ascstb.whatpeoplesearch.model.GoogleAnswer
import com.ascstb.whatpeoplesearch.repository.GoogleRepository
import timber.log.Timber

class AnswerViewModel(private val repository: GoogleRepository) : ViewModel() {
    private var language: String = "en"
    private var query: String = ""
    var questions: List<String> = emptyList()
    var currentQuestion: String = ""
    var guess: String = ""
        set(value) {
            if (field == value) return
            field = value.replace(currentQuestion, "").toLowerCase().trim()
        }

    val answersList: LiveData<List<GoogleAnswer>>
        get() = repository.getAnswersAsync(
            language = language,
            query = query
        )

    var updateAnswer: MutableLiveData<Boolean> = MutableLiveData()

    fun updateParameters(language: String = "", query: String = "") {
        this.language = if (language.isNotEmpty()) language else this.language
        this.query = if (query.isNotEmpty()) query else this.query
    }

    fun tryGuessing() {
        Timber.d("GameView_TAG: tryGuessing: Current Question: $currentQuestion, Guess: $guess")
        var correctGuess = false
        Game.answers.forEach {
            if (it.answer.toLowerCase().trim() == guess) {
                it.uncovered = true
                correctGuess = true
                Game.currentPlayer.score += it.points
            }
        }

        updateAnswer.postValue(correctGuess)
    }
}