package com.ganggym.bamagang.workout.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface RepsTarget {
    @Serializable
    @SerialName("exact")
    data class Exact(val reps: Int) : RepsTarget        // 10

    @Serializable
    @SerialName("range")
    data class Range(val min: Int, val max: Int) : RepsTarget  // 8-12

    @Serializable
    @SerialName("amrap")
    data object AMRAP : RepsTarget                       // на максимум
}
