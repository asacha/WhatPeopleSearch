package com.ascstb.whatpeoplesearch.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ascstb.whatpeoplesearch.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class RootActivity : AppCompatActivity() {

    private val viewModel by viewModel<AnswerViewModel>()
    //private val repository by inject<GoogleRepository>()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.root_activity_layout)

        viewModel.updateParameters(
            language = "en",
            query = "life is "
        )

        viewModel.answersList.observe(this, Observer { answers ->
            Timber.d("RootActivity_TAG: onCreate: answers: $answers")
        })
    }
}
