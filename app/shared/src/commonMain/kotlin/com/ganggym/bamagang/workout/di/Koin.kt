package com.ganggym.bamagang.workout.di

import com.ganggym.bamagang.workout.data.di.dataModule
import com.ganggym.bamagang.workout.data.di.platformDataModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(dataModule, platformDataModule())
    }
}
