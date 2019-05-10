package com.ascstb.whatpeoplesearch.di

import com.ascstb.whatpeoplesearch.presentation.categories.CategorySelectionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val categorySelectionModule = module {
    viewModel { CategorySelectionViewModel(get()) }
}