package com.ganggym.bamagang.workout.domain.repository

import com.ganggym.bamagang.workout.domain.model.Exercise
import com.ganggym.bamagang.workout.domain.model.ExerciseFilter
import com.ganggym.bamagang.workout.domain.util.ExerciseId
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    fun observe(filter: ExerciseFilter = ExerciseFilter()): Flow<List<Exercise>>
    suspend fun get(id: ExerciseId): Exercise?
    suspend fun save(exercise: Exercise)
    suspend fun delete(id: ExerciseId)
}