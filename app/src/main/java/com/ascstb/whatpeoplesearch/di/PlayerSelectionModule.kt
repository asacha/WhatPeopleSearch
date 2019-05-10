package com.ascstb.whatpeoplesearch.di

import com.ascstb.whatpeoplesearch.presentation.players.PlayerSelectionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val playerSelectionModule = module {
    viewModel { PlayerSelectionViewModel(get()) }
}