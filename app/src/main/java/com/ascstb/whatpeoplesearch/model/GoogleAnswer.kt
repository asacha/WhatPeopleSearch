package com.ascstb.whatpeoplesearch.model

data class GoogleAnswer (
    val position: Int = 0,
    val answer: String = "",
    val points: Int = 0,
    var uncovered: Boolean = false
)