package com.ascstb.whatpeoplesearch.presentation.categories

import androidx.lifecycle.MutableLiveData
import com.ascstb.whatpeoplesearch.core.BaseViewModel
import com.ascstb.whatpeoplesearch.core.Navigation
import com.ascstb.whatpeoplesearch.model.Category
import com.ascstb.whatpeoplesearch.core.Game
import timber.log.Timber

class CategorySelectionViewModel(
    private val navigation: Navigation
) : BaseViewModel() {

    var userName: MutableLiveData<String> = MutableLiveData()
        set(value) {
        if (field == value) return
        field = value
    }

    fun onConfirm(category: Category) {
        Timber.d("CategorySelectionViewModel_TAG: onConfirm: $category")
        Game.currentCategory = category
        navigation.navigateToGame()
    }
}