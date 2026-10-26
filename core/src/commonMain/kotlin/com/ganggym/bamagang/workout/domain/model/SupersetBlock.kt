package com.ganggym.bamagang.workout.domain.model

import com.ganggym.bamagang.workout.domain.util.WorkoutBlockId
import kotlinx.serialization.Serializable
import kotlin.time.Duration

@Serializable
data class SupersetBlock(
    override val id: WorkoutBlockId,
    override val note: String = "",
    override val name: String = "",
    override val target: SetTarget? = null,
    override val rounds: Int = 1,
    override val restBetweenRounds: Duration = Duration.ZERO,
    override val restAfter: Duration = Duration.ZERO,
    override val items: List<GroupItem> = emptyList(),
) : GroupBlock
