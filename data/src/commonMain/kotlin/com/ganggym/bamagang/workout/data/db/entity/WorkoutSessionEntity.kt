package com.ganggym.bamagang.workout.data.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "workout_sessions",
    indices = [Index("startedAt")],
)
data class WorkoutSessionEntity(
    @PrimaryKey val id: String,
    val ownerId: String,
    val workoutId: String?,
    val plan: String,            // JSON знімка
    val startedAt: Long,
    val completedAt: Long?,
    val feeling: String?,
    val notes: String,
)