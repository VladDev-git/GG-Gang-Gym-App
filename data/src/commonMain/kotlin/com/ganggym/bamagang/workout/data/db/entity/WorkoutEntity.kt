package com.ganggym.bamagang.workout.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class WorkoutEntity(
    @PrimaryKey val id: String,
    val ownerId: String?,
    val name: String,
    val description: String,
    val categoryIds: String,     // JSON
    val equipmentIds: String,    // JSON
    val difficulty: String,
    val visibility: String,
    val source: String,
    val blocks: String,          // JSON
    val icon: String?,
    val createdAt: Long,
    val updatedAt: Long,
    val deletedAt: Long?,
)
