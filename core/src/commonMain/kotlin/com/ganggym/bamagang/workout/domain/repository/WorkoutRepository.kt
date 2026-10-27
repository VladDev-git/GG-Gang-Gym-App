package com.ganggym.bamagang.workout.domain.repository

import com.ganggym.bamagang.workout.domain.model.Workout
import com.ganggym.bamagang.workout.domain.util.WorkoutId
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {
    fun observeAll(): Flow<List<Workout>>
    suspend fun get(id: WorkoutId): Workout?
    suspend fun save(workout: Workout)
    suspend fun delete(id: WorkoutId)
}