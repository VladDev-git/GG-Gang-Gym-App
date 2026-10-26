package com.ganggym.bamagang.workout.domain.util

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable @JvmInline value class UserId(val value: String)
@Serializable @JvmInline value class ExerciseId(val value: String)
@Serializable @JvmInline value class ExerciseCategoryId(val value: String)
@Serializable @JvmInline value class EquipmentId(val value: String)
@Serializable @JvmInline value class MuscleGroupId(val value: String)
@Serializable @JvmInline value class WorkoutId(val value: String)
@Serializable @JvmInline value class WorkoutBlockId(val value: String)
@Serializable @JvmInline value class RoutineId(val value: String)
@Serializable @JvmInline value class WorkoutSessionId(val value: String)
@Serializable @JvmInline value class ExerciseResultId(val value: String)
