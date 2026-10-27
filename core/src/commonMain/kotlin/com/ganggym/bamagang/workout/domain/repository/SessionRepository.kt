package com.ganggym.bamagang.workout.domain.repository

import com.ganggym.bamagang.workout.domain.model.ExerciseResult
import com.ganggym.bamagang.workout.domain.model.WorkoutSession
import com.ganggym.bamagang.workout.domain.util.ExerciseId
import com.ganggym.bamagang.workout.domain.util.WorkoutSessionId
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    fun observeAll(): Flow<List<WorkoutSession>>
    fun observeActive(): Flow<WorkoutSession?>
    suspend fun get(id: WorkoutSessionId): WorkoutSession?
    suspend fun save(session: WorkoutSession)
    fun observeHistory(exerciseId: ExerciseId, limit: Int = 50): Flow<List<ExerciseResult>>
}
