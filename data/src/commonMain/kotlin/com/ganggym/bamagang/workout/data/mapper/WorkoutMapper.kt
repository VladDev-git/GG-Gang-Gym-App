package com.ganggym.bamagang.workout.data.mapper

import com.ganggym.bamagang.workout.data.db.entity.WorkoutEntity
import com.ganggym.bamagang.workout.domain.model.DifficultyLevel
import com.ganggym.bamagang.workout.domain.model.Workout
import com.ganggym.bamagang.workout.domain.model.WorkoutSource
import com.ganggym.bamagang.workout.domain.model.WorkoutVisibility
import com.ganggym.bamagang.workout.domain.util.UserId
import com.ganggym.bamagang.workout.domain.util.WorkoutId

fun WorkoutEntity.toDomain() = Workout(
    id = WorkoutId(id),
    ownerId = ownerId?.let(::UserId),
    name = name,
    description = description,
    categoryIds = dbJson.decodeFromString(categoryIds),
    equipmentIds = dbJson.decodeFromString(equipmentIds),
    difficulty = difficulty.toEnumOr(DifficultyLevel.NOT_SET),
    visibility = visibility.toEnumOr(WorkoutVisibility.PRIVATE),
    source = source.toEnumOr(WorkoutSource.CUSTOM),
    blocks = dbJson.decodeFromString(blocks),
    audit = auditOf(createdAt, updatedAt, deletedAt),
    icon = icon,
)

fun Workout.toEntity() = WorkoutEntity(
    id = id.value,
    ownerId = ownerId?.value,
    name = name,
    description = description,
    categoryIds = dbJson.encodeToString(categoryIds),
    equipmentIds = dbJson.encodeToString(equipmentIds),
    difficulty = difficulty.name,
    visibility = visibility.name,
    source = source.name,
    blocks = dbJson.encodeToString(blocks),
    icon = icon,
    createdAt = audit.createdAt.toEpochMilliseconds(),
    updatedAt = audit.updatedAt.toEpochMilliseconds(),
    deletedAt = audit.deletedAt?.toEpochMilliseconds(),
)
