package com.ascstb.whatpeoplesearch.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ascstb.whatpeoplesearch.R
import com.ascstb.whatpeoplesearch.core.Navigation
import com.ascstb.whatpeoplesearch.presentation.categories.CategorySelectionView
import com.ascstb.whatpeoplesearch.presentation.players.PlayerSelectionView
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class RootActivity : AppCompatActivity(), Navigation {

    private val viewModel by viewModel<AnswerViewModel>()
    private lateinit var currentScreen: Navigation.Screen
    //private val repository by inject<GoogleRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.root_activity_layout)

        viewModel.updateParameters(
            language = Locale.getDefault().language
        )

        /*viewModel.answersList.observe(this, Observer { answers ->
            Timber.d("RootActivity_TAG: onCreate: answers: $answers")
        })*/
        navigateToUserSelection()
    }

    override fun navigateToUserSelection() {
        loadContent(
            fragment = PlayerSelectionView(),
            tag = Navigation.Screen.PLAYER_SELECTION
        )
    }

    override fun navigateToCategorySelection() {
        loadContent(
            fragment = CategorySelectionView(),
            tag = Navigation.Screen.CATEGORY_SELECTION
        )
    }

    override fun navigateToGame() {

    }

    override fun onBackPressed() {
        if (currentScreen == Navigation.Screen.PLAYER_SELECTION)
            supportFragmentManager.popBackStack()

        super.onBackPressed()
    }

    private fun loadContent(fragment: Fragment, tag: Navigation.Screen, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, fragment, tag.tag)

        if (addToBackStack) transaction.addToBackStack(tag.tag)

        transaction.commit()
        currentScreen = tag
    }
}
