package com.ascstb.whatpeoplesearch.di

import com.ascstb.whatpeoplesearch.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val apiModule = module {
    single { createOkHttpClient() }
}

fun createOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { level = logLevel })
    .readTimeout(120, TimeUnit.SECONDS)
    .build()

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T =
    Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
        .build()
        .create(T::class.java)


private val logLevel =
    if (!BuildConfig.DEBUG) HttpLoggingInterceptor.Level.NONE
    else HttpLoggingInterceptor.Level.BODY