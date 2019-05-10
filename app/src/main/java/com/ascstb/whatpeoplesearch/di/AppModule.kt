package com.ascstb.whatpeoplesearch.di

import com.ascstb.whatpeoplesearch.core.Navigation
import com.ascstb.whatpeoplesearch.core.NavigationImpl
import org.koin.dsl.module

val appModule = module {
    single<Navigation> { NavigationImpl() }
}