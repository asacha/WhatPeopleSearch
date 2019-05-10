package com.ascstb.whatpeoplesearch.di

import com.ascstb.whatpeoplesearch.presentation.game.AnswerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val gameModule = module {
    viewModel { AnswerViewModel(get()) }
}