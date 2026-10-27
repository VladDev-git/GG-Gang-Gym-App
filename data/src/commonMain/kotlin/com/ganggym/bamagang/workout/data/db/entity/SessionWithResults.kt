package com.ganggym.bamagang.workout.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class SessionWithResults(
    @Embedded val session: WorkoutSessionEntity,
    @Relation(parentColumn = "id", entityColumn = "sessionId")
    val results: List<ExerciseResultEntity>,
)
