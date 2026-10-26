package com.ganggym.bamagang.workout.domain.model

import com.ganggym.bamagang.workout.domain.util.ExerciseCategoryId

data class ExerciseCategory(
    val id: ExerciseCategoryId,
    val name: String,
    val iconName: String? = null,
)
