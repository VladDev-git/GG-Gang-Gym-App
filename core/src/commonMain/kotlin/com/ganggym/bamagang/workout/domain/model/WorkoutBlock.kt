package com.ganggym.bamagang.workout.domain.model

import com.ganggym.bamagang.workout.domain.util.WorkoutBlockId
import kotlinx.serialization.Serializable

@Serializable
sealed interface WorkoutBlock {
    val id: WorkoutBlockId
    val note: String
}
