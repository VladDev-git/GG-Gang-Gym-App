package com.ganggym.bamagang.workout.domain.repository

import com.ganggym.bamagang.workout.domain.model.Routine
import com.ganggym.bamagang.workout.domain.util.RoutineId
import kotlinx.coroutines.flow.Flow

interface RoutineRepository {
    fun observeAll(): Flow<List<Routine>>
    suspend fun get(id: RoutineId): Routine?
    suspend fun save(routine: Routine)
    suspend fun delete(id: RoutineId)
}