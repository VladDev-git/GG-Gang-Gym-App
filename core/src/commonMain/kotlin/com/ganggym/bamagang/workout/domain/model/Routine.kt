package com.ganggym.bamagang.workout.domain.model

import com.ganggym.bamagang.workout.domain.util.RoutineId
import com.ganggym.bamagang.workout.domain.util.UserId

data class Routine(
    val id: RoutineId,
    val ownerId: UserId,
    val name: String,
    val description: String = "",
    val blocks: List<WorkoutBlock> = emptyList(),
    val audit: AuditInfo,
    val icon: String? = null,
)
