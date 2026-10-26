package com.ganggym.bamagang.workout.domain.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface RepsTarget {
    @Serializable
    data class Exact(val reps: Int) : RepsTarget        // 10

    @Serializable
    data class Range(val min: Int, val max: Int) : RepsTarget  // 8-12

    @Serializable
    data object AMRAP : RepsTarget                       // на максимум
}
