package com.ascstb.whatpeoplesearch.core

import android.util.SparseArray
import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import timber.log.Timber

open class BaseViewModel: ViewModel(), Observable {
    private val onChangeObservers = SparseArray<MutableList<() -> Unit>>()

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        Timber.d("BaseViewModel_TAG: addOnPropertyChangedCallback")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        Timber.d("BaseViewModel_TAG: removeOnPropertyChangedCallback")
    }
}