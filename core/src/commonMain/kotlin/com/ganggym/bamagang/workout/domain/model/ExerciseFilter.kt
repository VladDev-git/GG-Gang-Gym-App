package com.ganggym.bamagang.workout.domain.model

import com.ganggym.bamagang.workout.domain.util.EquipmentId
import com.ganggym.bamagang.workout.domain.util.ExerciseCategoryId
import com.ganggym.bamagang.workout.domain.util.MuscleGroupId

data class ExerciseFilter(
    val muscleIds: Set<MuscleGroupId> = emptySet(),
    val equipmentIds: Set<EquipmentId> = emptySet(),
    val categoryIds: Set<ExerciseCategoryId> = emptySet(),
    val query: String = "",
)
