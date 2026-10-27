package com.ganggym.bamagang.workout.data.db

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseBuilderFactory {
    actual fun create(): RoomDatabase.Builder<AppDatabase> {
        val dir = File(System.getProperty("user.home"), ".ganggym").apply { mkdirs() }
        return Room.databaseBuilder<AppDatabase>(File(dir, DB_FILE_NAME).absolutePath)
    }
}
