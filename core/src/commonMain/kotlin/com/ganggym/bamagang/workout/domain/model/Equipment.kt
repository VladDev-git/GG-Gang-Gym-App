package com.ganggym.bamagang.workout.domain.model

import com.ganggym.bamagang.workout.domain.util.EquipmentId

data class Equipment(
    val id: EquipmentId,
    val name: String,
    val iconName: String? = null,
)
