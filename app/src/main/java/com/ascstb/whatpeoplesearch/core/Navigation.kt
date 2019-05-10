package com.ascstb.whatpeoplesearch.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ascstb.whatpeoplesearch.R
import com.ascstb.whatpeoplesearch.presentation.categories.CategorySelectionView
import com.ascstb.whatpeoplesearch.presentation.game.GameView
import com.ascstb.whatpeoplesearch.presentation.players.PlayerSelectionView
import timber.log.Timber

interface Navigation {
    fun setFragmentManager(fragmentManager: FragmentManager)
    fun navigateToUserSelection() {}
    fun navigateToCategorySelection() {}
    fun navigateToGame() {}

    enum class Screen(val tag: String) {
        CATEGORY_SELECTION("category_selection"),
        GAME("game"),
        PLAYER_SELECTION("player_selection")
    }
}

class NavigationImpl : Navigation {
    private lateinit var currentScreen: Navigation.Screen
    private lateinit var fragmentManager: FragmentManager

    override fun setFragmentManager(fragmentManager: FragmentManager) {
        this.fragmentManager = fragmentManager
    }

    override fun navigateToUserSelection() {
        super.navigateToUserSelection()
        Timber.d("NavigationImpl_TAG: navigateToUserSelection")
        loadContent(
            fragment = PlayerSelectionView(),
            tag = Navigation.Screen.PLAYER_SELECTION
        )
    }

    override fun navigateToCategorySelection() {
        super.navigateToCategorySelection()
        Timber.d("NavigationImpl_TAG: navigateToCategorySelection")
        loadContent(
            fragment = CategorySelectionView(),
            tag = Navigation.Screen.CATEGORY_SELECTION
        )
    }

    override fun navigateToGame() {
        super.navigateToGame()
        Timber.d("NavigationImpl_TAG: navigateToGame")
        loadContent(
            fragment = GameView(),
            tag = Navigation.Screen.GAME
        )
    }

    private fun loadContent(fragment: Fragment, tag: Navigation.Screen, addToBackStack: Boolean = true) {
        val transaction = fragmentManager
            .beginTransaction()
            .replace(R.id.content, fragment, tag.tag)

        if (addToBackStack) transaction.addToBackStack(tag.tag)

        transaction.commit()
        currentScreen = tag
    }
}