package com.ganggym.bamagang.workout.data.db.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ExerciseWithRefs(
    @Embedded val exercise: ExerciseEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = ExerciseMuscleCrossRef::class,
            parentColumn = "exerciseId",
            entityColumn = "muscleId",
        ),
    )
    val muscles: List<MuscleGroupEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = ExerciseEquipmentCrossRef::class,
            parentColumn = "exerciseId",
            entityColumn = "equipmentId",
        ),
    )
    val equipment: List<EquipmentEntity>,
)