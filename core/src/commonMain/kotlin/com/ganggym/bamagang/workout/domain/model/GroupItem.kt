package com.ganggym.bamagang.workout.domain.model

import com.ganggym.bamagang.workout.domain.util.ExerciseId
import kotlinx.serialization.Serializable
import kotlin.time.Duration

@Serializable
data class GroupItem(
    val exerciseId: ExerciseId,
    val sets: List<SetTarget> = emptyList(),  // порожній → діє target групи
    val restAfter: Duration = Duration.ZERO,  // пауза перед наступною вправою
)
