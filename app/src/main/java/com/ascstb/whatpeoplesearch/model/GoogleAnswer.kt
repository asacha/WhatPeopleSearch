package com.ascstb.whatpeoplesearch.model

data class GoogleAnswer (
    val position: Int = 0,
    val answer: String = "",
    var uncovered: Boolean = false
)