package com.ascstb.whatpeoplesearch.presentation.players

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import com.ascstb.whatpeoplesearch.core.BaseViewModel
import com.ascstb.whatpeoplesearch.core.Navigation

class PlayerSelectionViewModel(
    private val navigation: Navigation
) : BaseViewModel() {
    @get:Bindable
    var users: Int = 1
        set(value) {
            if (field == value) return
            field = value
        }

    fun onConfirm() {
        navigation.navigateToCategorySelection()
    }
}