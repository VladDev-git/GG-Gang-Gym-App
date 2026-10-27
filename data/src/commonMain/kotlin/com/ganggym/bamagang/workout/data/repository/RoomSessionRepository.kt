package com.ganggym.bamagang.workout.data.repository

import com.ganggym.bamagang.workout.data.db.dao.SessionDao
import com.ganggym.bamagang.workout.data.db.entity.SessionWithResults
import com.ganggym.bamagang.workout.data.mapper.toDomain
import com.ganggym.bamagang.workout.data.mapper.toDomainOrNull
import com.ganggym.bamagang.workout.data.mapper.toEntity
import com.ganggym.bamagang.workout.domain.model.ExerciseResult
import com.ganggym.bamagang.workout.domain.model.WorkoutSession
import com.ganggym.bamagang.workout.domain.repository.SessionRepository
import com.ganggym.bamagang.workout.domain.util.ExerciseId
import com.ganggym.bamagang.workout.domain.util.WorkoutSessionId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class RoomSessionRepository(
    private val dao: SessionDao,
) : SessionRepository {

    override fun observeAll(): Flow<List<WorkoutSession>> =
        dao.observeAll().map { list -> list.mapNotNull { it.toDomainOrNull() } }

    override fun observeActive(): Flow<WorkoutSession?> =
        dao.observeActive().map { it?.toDomainOrNull() }

    override suspend fun get(id: WorkoutSessionId): WorkoutSession? =
        dao.find(id.value)?.toDomainOrNull()

    override suspend fun save(session: WorkoutSession) =
        dao.saveFull(
            session = session.toEntity(),
            results = session.results.map { it.toEntity(session.id) },
        )

    override fun observeHistory(exerciseId: ExerciseId, limit: Int): Flow<List<ExerciseResult>> =
        dao.observeHistory(exerciseId.value, limit).map { list -> list.mapNotNull { it.toDomainOrNull() } }
}

private fun SessionWithResults.toDomainOrNull(): WorkoutSession? =
    runCatching { session.toDomain(results) }.getOrNull()
