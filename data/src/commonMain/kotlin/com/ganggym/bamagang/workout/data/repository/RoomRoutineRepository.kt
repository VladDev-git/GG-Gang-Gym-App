package com.ganggym.bamagang.workout.data.repository

import com.ganggym.bamagang.workout.data.db.dao.RoutineDao
import com.ganggym.bamagang.workout.data.db.entity.RoutineEntity
import com.ganggym.bamagang.workout.data.mapper.toDomain
import com.ganggym.bamagang.workout.data.mapper.toEntity
import com.ganggym.bamagang.workout.domain.model.Routine
import com.ganggym.bamagang.workout.domain.repository.RoutineRepository
import com.ganggym.bamagang.workout.domain.util.RoutineId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.time.Clock

internal class RoomRoutineRepository(
    private val dao: RoutineDao,
    private val clock: Clock,
) : RoutineRepository {
    override fun observeAll(): Flow<List<Routine>> =
        dao.observeAll().map { list -> list.mapNotNull { it.toDomainOrNull() } }

    override suspend fun get(id: RoutineId): Routine? =
        dao.find(id.value)?.toDomainOrNull()

    override suspend fun save(routine: Routine) =
        dao.upsert(routine.toEntity())

    override suspend fun delete(id: RoutineId) =
        dao.softDelete(id.value, clock.now().toEpochMilliseconds())
}

private fun RoutineEntity.toDomainOrNull(): Routine? = runCatching { toDomain() }.getOrNull()