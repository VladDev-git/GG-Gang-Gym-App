package com.ganggym.bamagang.workout.data.mapper

import com.ganggym.bamagang.workout.data.db.entity.ExerciseCategoryCrossRef
import com.ganggym.bamagang.workout.data.db.entity.ExerciseEntity
import com.ganggym.bamagang.workout.data.db.entity.ExerciseEquipmentCrossRef
import com.ganggym.bamagang.workout.data.db.entity.ExerciseMuscleCrossRef
import com.ganggym.bamagang.workout.data.db.entity.ExerciseWithRefs
import com.ganggym.bamagang.workout.domain.model.DifficultyLevel
import com.ganggym.bamagang.workout.domain.model.Exercise
import com.ganggym.bamagang.workout.domain.model.TrackingType
import com.ganggym.bamagang.workout.domain.util.EquipmentId
import com.ganggym.bamagang.workout.domain.util.ExerciseCategoryId
import com.ganggym.bamagang.workout.domain.util.ExerciseId
import com.ganggym.bamagang.workout.domain.util.MuscleGroupId
import com.ganggym.bamagang.workout.domain.util.UserId

fun ExerciseWithRefs.toDomain() = Exercise(
    id = ExerciseId(exercise.id),
    name = exercise.name,
    tracking = TrackingType.valueOf(exercise.tracking),
    description = exercise.description,
    imageUrl = exercise.imageUrl,
    categoryIds = categories.map { ExerciseCategoryId(it.categoryId) }.toSet(),
    equipmentIds = equipment.map { EquipmentId(it.equipmentId) }.toSet(),
    primaryMuscleIds = muscles.filter { it.isPrimary }.map { MuscleGroupId(it.muscleId) }.toSet(),
    secondaryMuscleIds = muscles.filterNot { it.isPrimary }.map { MuscleGroupId(it.muscleId) }.toSet(),
    difficulty = exercise.difficulty.toEnumOr(DifficultyLevel.NOT_SET),
    ownerId = exercise.ownerId?.let(::UserId),
    audit = auditOf(exercise.createdAt, exercise.updatedAt, exercise.deletedAt),
)

fun Exercise.toEntity(): ExerciseWithRefs = ExerciseWithRefs(
    exercise = ExerciseEntity(
        id = id.value,
        name = name,
        tracking = tracking.name,
        description = description,
        imageUrl = imageUrl,
        difficulty = difficulty.name,
        ownerId = ownerId?.value,
        createdAt = audit.createdAt.toEpochMilliseconds(),
        updatedAt = audit.updatedAt.toEpochMilliseconds(),
        deletedAt = audit.deletedAt?.toEpochMilliseconds(),
    ),
    muscles = primaryMuscleIds.map { ExerciseMuscleCrossRef(id.value, it.value, isPrimary = true) } +
            secondaryMuscleIds.map { ExerciseMuscleCrossRef(id.value, it.value, isPrimary = false) },
    equipment = equipmentIds.map { ExerciseEquipmentCrossRef(id.value, it.value) },
    categories = categoryIds.map { ExerciseCategoryCrossRef(id.value, it.value) },
)
