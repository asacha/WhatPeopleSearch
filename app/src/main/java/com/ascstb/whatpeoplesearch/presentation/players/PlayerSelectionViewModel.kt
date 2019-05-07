package com.ascstb.whatpeoplesearch.presentation.players

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.ViewModel

class PlayerSelectionViewModel : ViewModel(), Observable {

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    @get:Bindable
    var users: Int = 1
    set(value) {
        if (field == value) return
        field = value
    }

    fun onConfirm() {
//        navigation.navigateToCategorySelection()
    }
}