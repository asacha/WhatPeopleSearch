package com.ascstb.whatpeoplesearch.model

object Game {
    var users: List<User> = listOf()
    var turn: Int = 0

    val currentUser: User
        get() = users[turn]

    var currentCategory: Category = Category.NOT_FOUND

    var answers: List<GoogleAnswer> = emptyList()
}