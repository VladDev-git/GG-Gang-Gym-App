package com.ganggym.bamagang.workout.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercise_results",
    foreignKeys = [
        ForeignKey(WorkoutSessionEntity::class, ["id"], ["sessionId"], onDelete = ForeignKey.CASCADE),
    ],
    indices = [Index("sessionId"), Index("exerciseId", "completedAt")],
)
data class ExerciseResultEntity(
    @PrimaryKey val id: String,
    val sessionId: String,
    val exerciseId: String?,
    val blockId: String?,
    val round: Int?,
    val itemIndex: Int?,
    val setIndex: Int?,
    val type: String,
    val reps: Int?,
    val weightKg: Double?,
    val durationMillis: Long?,
    val distanceMeters: Double?,
    val rpe: Double?,
    val completedAt: Long,
    val note: String,
)
