package com.ganggym.bamagang.workout.di

import com.ganggym.bamagang.workout.data.di.dataModule
import com.ganggym.bamagang.workout.data.di.platformDataModule
import com.ganggym.bamagang.workout.data.seed.SeedSource
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

private val appModule = module {
    single<SeedSource> { ResourceSeedSource() }
}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(dataModule, platformDataModule(), appModule)
    }
}
