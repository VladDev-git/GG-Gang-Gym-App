package com.ganggym.bamagang.workout.domain.model

import kotlinx.serialization.Serializable
import kotlin.time.Duration

@Serializable
data class SetTarget(
    val type: SetType = SetType.WORKING,
    val reps: RepsTarget? = null,
    val load: LoadTarget? = null,
    val duration: Duration? = null,
    val distanceMeters: Double? = null,
    val rpe: Double? = null,
    val restAfter: Duration? = null,   // null → беремо з блоку
)
