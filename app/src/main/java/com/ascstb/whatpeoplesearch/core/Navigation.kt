package com.ascstb.whatpeoplesearch.core

interface Navigation {
    fun navigateToUserSelection()
    fun navigateToCategorySelection()
    fun navigateToGame()

    enum class Screen(val tag: String) {
        CATEGORY_SELECTION("category_selection"),
        GAME("game"),
        PLAYER_SELECTION("player_selection")
    }
}