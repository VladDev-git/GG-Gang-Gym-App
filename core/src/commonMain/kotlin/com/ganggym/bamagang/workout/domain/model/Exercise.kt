package com.ganggym.bamagang.workout.domain.model

import com.ganggym.bamagang.workout.domain.util.EquipmentId
import com.ganggym.bamagang.workout.domain.util.ExerciseCategoryId
import com.ganggym.bamagang.workout.domain.util.ExerciseId
import com.ganggym.bamagang.workout.domain.util.MuscleGroupId
import com.ganggym.bamagang.workout.domain.util.UserId

data class Exercise(
    val id: ExerciseId,
    val name: String,
    val tracking: TrackingType,
    val description: String = "",
    val steps: List<String> = emptyList(),   // кроки техніки виконання
    val mistakes: List<String> = emptyList(), // типові помилки
    val imageUrl: String? = null,
    val categoryIds: Set<ExerciseCategoryId> = emptySet(),
    val equipmentIds: Set<EquipmentId> = emptySet(),
    val primaryMuscleIds: Set<MuscleGroupId> = emptySet(),
    val secondaryMuscleIds: Set<MuscleGroupId> = emptySet(),
    val difficulty: DifficultyLevel = DifficultyLevel.NOT_SET,
    val ownerId: UserId? = null,    // null → вбудована вправа
    val audit: AuditInfo,
) {
    val isCustom: Boolean get() = ownerId != null
}
