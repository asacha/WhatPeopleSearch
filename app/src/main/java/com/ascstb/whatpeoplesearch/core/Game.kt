package com.ascstb.whatpeoplesearch.core

import com.ascstb.whatpeoplesearch.model.Category
import com.ascstb.whatpeoplesearch.model.GoogleAnswer
import com.ascstb.whatpeoplesearch.model.Player

object Game {
    var players: List<Player> = listOf()
    var turn: Int = 0
        set(value) {
            field = if (value < players.size) value else 0
        }

    val currentPlayer: Player
        get() = players[turn]

    var currentCategory: Category =
        Category.NOT_FOUND

    var answers: List<GoogleAnswer> = emptyList()

    fun printCurrentPlayer() = "${currentPlayer.name}: ${currentPlayer.score}"
}