package com.ganggym.bamagang.workout.domain.model

import com.ganggym.bamagang.workout.domain.util.ExerciseId
import com.ganggym.bamagang.workout.domain.util.WorkoutBlockId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Duration

@Serializable
@SerialName("exercise")
data class ExerciseBlock(
    override val id: WorkoutBlockId,
    override val note: String = "",
    val exerciseId: ExerciseId,
    val sets: List<SetTarget> = emptyList(),
    val restAfter: Duration = Duration.ZERO,
) : WorkoutBlock
