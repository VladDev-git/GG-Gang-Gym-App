package com.ganggym.bamagang.workout.data.repository

import com.ganggym.bamagang.workout.data.db.dao.ExerciseDao
import com.ganggym.bamagang.workout.data.mapper.toDomain
import com.ganggym.bamagang.workout.data.mapper.toEntity
import com.ganggym.bamagang.workout.domain.model.Exercise
import com.ganggym.bamagang.workout.domain.model.ExerciseFilter
import com.ganggym.bamagang.workout.domain.repository.ExerciseRepository
import com.ganggym.bamagang.workout.domain.util.ExerciseId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.time.Clock

internal class RoomExerciseRepository(
    private val dao: ExerciseDao,
    private val clock: Clock,
) : ExerciseRepository {

    override fun observe(filter: ExerciseFilter): Flow<List<Exercise>> =
        dao.filterRaw(
            muscleIds = filter.muscleIds.map { it.value },
            ignoreMuscles = filter.muscleIds.isEmpty(),
            equipmentIds = filter.equipmentIds.map { it.value },
            ignoreEquipment = filter.equipmentIds.isEmpty(),
            categoryIds = filter.categoryIds.map { it.value },
            ignoreCategories = filter.categoryIds.isEmpty(),
            query = filter.query.trim(),
        ).map { list -> list.mapNotNull { runCatching { it.toDomain() }.getOrNull() } }

    override suspend fun get(id: ExerciseId): Exercise? =
        dao.findWithRefs(id.value)?.toDomain()

    override suspend fun save(exercise: Exercise) =
        dao.upsertFull(exercise.toEntity())

    override suspend fun delete(id: ExerciseId) {
        val current = get(id) ?: return
        save(current.copy(audit = current.audit.deleted(clock.now())))
    }
}