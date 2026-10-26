package com.ganggym.bamagang.workout.data.db

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

internal const val DB_FILE_NAME = "ganggym.db"

expect class DatabaseBuilderFactory {
    fun create(): RoomDatabase.Builder<AppDatabase>
}

fun RoomDatabase.Builder<AppDatabase>.buildAppDatabase(): AppDatabase =
    setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()