package com.ascstb.whatpeoplesearch.model

object Game {
    var users: List<User> = listOf()
    var categories: List<Category> = listOf()
    var turn: Int = 0

    val currentUser: User
        get() = users[turn]
}