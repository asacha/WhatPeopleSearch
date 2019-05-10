package com.ascstb.whatpeoplesearch.di

import com.ascstb.whatpeoplesearch.BuildConfig
import com.ascstb.whatpeoplesearch.repository.GoogleApi
import com.ascstb.whatpeoplesearch.repository.GoogleRepository
import com.ascstb.whatpeoplesearch.repository.GoogleRepositoryImpl
import org.koin.dsl.module

val googleServiceModule = module {
    single<GoogleApi> { createWebService(get(), BuildConfig.SERVER_URL) }
    single<GoogleRepository> { GoogleRepositoryImpl(get()) }
}