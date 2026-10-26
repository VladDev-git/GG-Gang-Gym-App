package com.ganggym.bamagang.workout.domain.model

import com.ganggym.bamagang.workout.domain.util.EquipmentId
import com.ganggym.bamagang.workout.domain.util.ExerciseCategoryId
import com.ganggym.bamagang.workout.domain.util.UserId
import com.ganggym.bamagang.workout.domain.util.WorkoutId

data class Workout(
    val id: WorkoutId,
    val ownerId: UserId? = null,    // null → вбудоване тренування
    val name: String,
    val description: String = "",
    val categoryIds: Set<ExerciseCategoryId> = emptySet(),
    val equipmentIds: Set<EquipmentId> = emptySet(),
    val difficulty: DifficultyLevel = DifficultyLevel.NOT_SET,
    val visibility: WorkoutVisibility = WorkoutVisibility.PRIVATE,
    val source: WorkoutSource = WorkoutSource.CUSTOM,
    val blocks: List<WorkoutBlock> = emptyList(),
    val audit: AuditInfo,
    val icon: String? = null,
)
