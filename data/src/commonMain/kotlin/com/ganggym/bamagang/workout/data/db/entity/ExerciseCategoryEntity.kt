package com.ganggym.bamagang.workout.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_categories")
data class ExerciseCategoryEntity(
    @PrimaryKey val id: String,
    val name: String,
    val iconName: String?,
    val sortOrder: Int,
)
