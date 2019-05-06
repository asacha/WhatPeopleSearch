package com.ascstb.whatpeoplesearch.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ascstb.whatpeoplesearch.model.GoogleAnswer
import com.ascstb.whatpeoplesearch.repository.GoogleRepository

class AnswerViewModel(private val repository: GoogleRepository) : ViewModel() {
    private var language: String = "en"
    private var query: String = ""

    val answersList: LiveData<List<GoogleAnswer>>
        get() = repository.getAnswersAsync(
            language = language,
            query = query
        )

    fun updateParameters(language: String = "", query: String = "") {
        this.language = if (language.isNotEmpty()) language else this.language
        this.query = if (query.isNotEmpty()) query else this.query
    }
}