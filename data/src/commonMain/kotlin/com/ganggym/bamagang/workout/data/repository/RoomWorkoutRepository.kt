package com.ganggym.bamagang.workout.data.repository

import com.ganggym.bamagang.workout.data.db.dao.WorkoutDao
import com.ganggym.bamagang.workout.data.db.entity.WorkoutEntity
import com.ganggym.bamagang.workout.data.mapper.toDomain
import com.ganggym.bamagang.workout.data.mapper.toEntity
import com.ganggym.bamagang.workout.domain.model.Workout
import com.ganggym.bamagang.workout.domain.repository.WorkoutRepository
import com.ganggym.bamagang.workout.domain.util.WorkoutId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.time.Clock

internal class RoomWorkoutRepository(
    private val dao: WorkoutDao,
    private val clock: Clock,
) : WorkoutRepository {

    override fun observeAll(): Flow<List<Workout>> =
        dao.observeAll().map { list -> list.mapNotNull { it.toDomainOrNull() } }

    override suspend fun get(id: WorkoutId): Workout? =
        dao.find(id.value)?.toDomainOrNull()

    override suspend fun save(workout: Workout) =
        dao.upsert(workout.toEntity())

    override suspend fun delete(id: WorkoutId) =
        dao.softDelete(id.value, clock.now().toEpochMilliseconds())
}

private fun WorkoutEntity.toDomainOrNull(): Workout? = runCatching { toDomain() }.getOrNull()
