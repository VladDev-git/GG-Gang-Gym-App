package com.ganggym.bamagang.workout.domain.model

import com.ganggym.bamagang.workout.domain.util.WorkoutBlockId
import kotlinx.serialization.Serializable
import kotlin.time.Duration

@Serializable
data class RestBlock(
    override val id: WorkoutBlockId,
    override val note: String = "",
    val duration: Duration,
) : WorkoutBlock
