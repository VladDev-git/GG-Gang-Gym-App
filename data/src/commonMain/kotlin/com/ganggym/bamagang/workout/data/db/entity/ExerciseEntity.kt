package com.ganggym.bamagang.workout.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey val id: String,
    val name: String,
    val tracking: String,
    val description: String,
    val imageUrl: String?,
    val difficulty: String,
    val ownerId: String?,
    val createdAt: Long,
    val updatedAt: Long,
    val deletedAt: Long?,
)

@Entity(
    tableName = "exercise_muscles",
    primaryKeys = ["exerciseId", "muscleId"],
    foreignKeys = [
        ForeignKey(ExerciseEntity::class, ["id"], ["exerciseId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(MuscleGroupEntity::class, ["id"], ["muscleId"], onDelete = ForeignKey.CASCADE),
    ],
    indices = [Index("muscleId")],
)
data class ExerciseMuscleCrossRef(
    val exerciseId: String,
    val muscleId: String,
    val isPrimary: Boolean,
)

@Entity(
    tableName = "exercise_equipment",
    primaryKeys = ["exerciseId", "equipmentId"],
    foreignKeys = [
        ForeignKey(ExerciseEntity::class, ["id"], ["exerciseId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(EquipmentEntity::class, ["id"], ["equipmentId"], onDelete = ForeignKey.CASCADE),
    ],
    indices = [Index("equipmentId")],
)
data class ExerciseEquipmentCrossRef(
    val exerciseId: String,
    val equipmentId: String,
)

@Entity(
    tableName = "exercise_category_refs",
    primaryKeys = ["exerciseId", "categoryId"],
    foreignKeys = [
        ForeignKey(ExerciseEntity::class, ["id"], ["exerciseId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(ExerciseCategoryEntity::class, ["id"], ["categoryId"], onDelete = ForeignKey.CASCADE),
    ],
    indices = [Index("categoryId")],
)
data class ExerciseCategoryCrossRef(
    val exerciseId: String,
    val categoryId: String,
)
