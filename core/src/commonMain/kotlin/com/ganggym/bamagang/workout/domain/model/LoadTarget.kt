package com.ganggym.bamagang.workout.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface LoadTarget {
    @Serializable
    @SerialName("kg")
    data class Kg(val value: Double) : LoadTarget

    @Serializable
    @SerialName("percent_1rm")
    data class PercentOf1RM(val percent: Double) : LoadTarget  // 75% від максимуму

    @Serializable
    @SerialName("bodyweight")
    data object Bodyweight : LoadTarget                        // підтягування

    @Serializable
    @SerialName("bodyweight_plus")
    data class BodyweightPlus(val extraKg: Double) : LoadTarget // +10 кг на поясі
}
