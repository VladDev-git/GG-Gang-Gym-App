package com.ganggym.bamagang.workout.domain.model

import kotlinx.serialization.Serializable
import kotlin.time.Duration

@Serializable
sealed interface GroupBlock : WorkoutBlock {
    val name: String
    val target: SetTarget?          // "Required" на рівні групи
    val rounds: Int
    val restBetweenRounds: Duration
    val restAfter: Duration
    val items: List<GroupItem>
}
