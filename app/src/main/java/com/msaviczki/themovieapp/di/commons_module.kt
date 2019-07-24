package com.msaviczki.themovieapp.di

import com.msaviczki.themovieapp.helper.adapter.CoroutineBaseContext
import com.msaviczki.themovieapp.helper.adapter.CoroutineBaseContextImpl
import org.koin.dsl.module

val commonsModule = module {
    factory { CoroutineBaseContextImpl() as CoroutineBaseContext }
}