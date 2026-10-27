package com.ganggym.bamagang.workout.data.mapper

import com.ganggym.bamagang.workout.data.db.entity.ExerciseResultEntity
import com.ganggym.bamagang.workout.data.db.entity.WorkoutSessionEntity
import com.ganggym.bamagang.workout.domain.model.ExerciseResult
import com.ganggym.bamagang.workout.domain.model.SetType
import com.ganggym.bamagang.workout.domain.model.WorkoutFeeling
import com.ganggym.bamagang.workout.domain.model.WorkoutSession
import com.ganggym.bamagang.workout.domain.util.ExerciseId
import com.ganggym.bamagang.workout.domain.util.ExerciseResultId
import com.ganggym.bamagang.workout.domain.util.UserId
import com.ganggym.bamagang.workout.domain.util.WorkoutBlockId
import com.ganggym.bamagang.workout.domain.util.WorkoutId
import com.ganggym.bamagang.workout.domain.util.WorkoutSessionId
import kotlin.time.Duration.Companion.milliseconds

fun WorkoutSessionEntity.toDomain(results: List<ExerciseResultEntity>) = WorkoutSession(
    id = WorkoutSessionId(id),
    ownerId = UserId(ownerId),
    workoutId = workoutId?.let(::WorkoutId),
    plan = dbJson.decodeFromString(plan),
    startedAt = startedAt.toInstant(),
    completedAt = completedAt?.toInstant(),
    feeling = feeling?.let { runCatching { WorkoutFeeling.valueOf(it) }.getOrNull() },
    notes = notes,
    results = results.mapNotNull { it.toDomainOrNull() },
)

fun WorkoutSession.toEntity() = WorkoutSessionEntity(
    id = id.value,
    ownerId = ownerId.value,
    workoutId = workoutId?.value,
    plan = dbJson.encodeToString(plan),
    startedAt = startedAt.toEpochMilliseconds(),
    completedAt = completedAt?.toEpochMilliseconds(),
    feeling = feeling?.name,
    notes = notes,
)

fun ExerciseResultEntity.toDomainOrNull(): ExerciseResult? = runCatching {
    ExerciseResult(
        id = ExerciseResultId(id),
        exerciseId = exerciseId?.let { ExerciseId(it) },
        blockId = blockId?.let(::WorkoutBlockId),
        setIndex = setIndex,
        itemIndex = itemIndex,
        round = round,
        type = type.toEnumOr(SetType.WORKING),
        reps = reps,
        weightKg = weightKg,
        duration = durationMillis?.milliseconds,
        distanceMeters = distanceMeters,
        rpe = rpe,
        completedAt = completedAt.toInstant(),
        note = note,
    )
}.getOrNull()

fun ExerciseResult.toEntity(sessionId: WorkoutSessionId) = ExerciseResultEntity(
    id = id.value,
    sessionId = sessionId.value,
    exerciseId = exerciseId?.value,
    blockId = blockId?.value,
    round = round,
    itemIndex = itemIndex,
    setIndex = setIndex,
    type = type.name,
    reps = reps,
    weightKg = weightKg,
    durationMillis = duration?.inWholeMilliseconds,
    distanceMeters = distanceMeters,
    rpe = rpe,
    completedAt = completedAt.toEpochMilliseconds(),
    note = note,
)
