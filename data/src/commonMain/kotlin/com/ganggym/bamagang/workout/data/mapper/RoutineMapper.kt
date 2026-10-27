package com.ganggym.bamagang.workout.data.mapper

import com.ganggym.bamagang.workout.data.db.entity.RoutineEntity
import com.ganggym.bamagang.workout.domain.model.Routine
import com.ganggym.bamagang.workout.domain.util.RoutineId
import com.ganggym.bamagang.workout.domain.util.UserId

fun RoutineEntity.toDomain() = Routine(
    id = RoutineId(id),
    ownerId = UserId(ownerId),
    name = name,
    description = description,
    blocks = dbJson.decodeFromString(blocks),
    audit = auditOf(createdAt, updatedAt, deletedAt),
    icon = icon,
)

fun Routine.toEntity() = RoutineEntity(
    id = id.value,
    ownerId = ownerId.value,
    name = name,
    description = description,
    blocks = dbJson.encodeToString(blocks),
    icon = icon,
    createdAt = audit.createdAt.toEpochMilliseconds(),
    updatedAt = audit.updatedAt.toEpochMilliseconds(),
    deletedAt = audit.deletedAt?.toEpochMilliseconds(),
)
