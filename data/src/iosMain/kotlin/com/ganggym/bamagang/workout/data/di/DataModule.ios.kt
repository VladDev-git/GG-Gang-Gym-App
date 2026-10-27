package com.ganggym.bamagang.workout.data.di

import com.ganggym.bamagang.workout.data.db.DatabaseBuilderFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformDataModule(): Module = module {
    single { DatabaseBuilderFactory() }
}
