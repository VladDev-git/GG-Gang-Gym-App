package com.ganggym.bamagang.workout.domain.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface LoadTarget {
    @Serializable
    data class Kg(val value: Double) : LoadTarget

    @Serializable
    data class PercentOf1RM(val percent: Double) : LoadTarget  // 75% від максимуму

    @Serializable
    data object Bodyweight : LoadTarget                        // підтягування

    @Serializable
    data class BodyweightPlus(val extraKg: Double) : LoadTarget // +10 кг на поясі
}
