package com.ganggym.bamagang.workout.domain.model

import com.ganggym.bamagang.workout.domain.util.MuscleGroupId

data class MuscleGroup(
    val id: MuscleGroupId,
    val name: String,
    val parentId: MuscleGroupId? = null,
    val icon: String? = null,
    val sortOrder: Int = 0,
)
