package com.ganggym.bamagang.workout.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ExerciseWithRefs(
    @Embedded val exercise: ExerciseEntity,

    @Relation(parentColumn = "id", entityColumn = "exerciseId")
    val muscles: List<ExerciseMuscleCrossRef>,

    @Relation(parentColumn = "id", entityColumn = "exerciseId")
    val equipment: List<ExerciseEquipmentCrossRef>,

    @Relation(parentColumn = "id", entityColumn = "exerciseId")
    val categories: List<ExerciseCategoryCrossRef>,
)
