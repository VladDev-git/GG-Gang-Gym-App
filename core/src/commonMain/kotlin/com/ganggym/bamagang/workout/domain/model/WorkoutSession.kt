package com.ganggym.bamagang.workout.domain.model

import com.ganggym.bamagang.workout.domain.util.ExerciseId
import com.ganggym.bamagang.workout.domain.util.UserId
import com.ganggym.bamagang.workout.domain.util.WorkoutId
import com.ganggym.bamagang.workout.domain.util.WorkoutSessionId
import kotlinx.serialization.Serializable
import kotlin.time.Duration
import kotlin.time.Instant

data class WorkoutSession(
    val id: WorkoutSessionId,
    val ownerId: UserId,
    val workoutId: WorkoutId? = null,
    val plan: SessionPlan,
    val startedAt: Instant,
    val completedAt: Instant? = null,
    val feeling: WorkoutFeeling? = null,
    val notes: String = "",
    val results: List<ExerciseResult> = emptyList(),
) {
    val duration: Duration? get() = completedAt?.let { it - startedAt }

    val isFinished: Boolean get() = completedAt != null
}

@Serializable
data class SessionPlan(
    val workoutName: String,
    val blocks: List<WorkoutBlock> = emptyList(),
    val exerciseNames: Map<ExerciseId, String>  = emptyMap(),
)
