package com.ganggym.bamagang.workout.data.repository

import com.ganggym.bamagang.workout.data.db.dao.CatalogDao
import com.ganggym.bamagang.workout.data.mapper.toDomain
import com.ganggym.bamagang.workout.domain.model.Equipment
import com.ganggym.bamagang.workout.domain.model.ExerciseCategory
import com.ganggym.bamagang.workout.domain.model.MuscleGroup
import com.ganggym.bamagang.workout.domain.repository.CatalogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class RoomCatalogRepository(
    private val dao: CatalogDao,
) : CatalogRepository {
    override fun observeEquipment(): Flow<List<Equipment>> =
        dao.observeEquipment().map { list -> list.map { it.toDomain() } }

    override fun observeMuscleGroups(): Flow<List<MuscleGroup>> =
        dao.observeMuscleGroups().map { list -> list.map { it.toDomain() } }

    override fun observeCategories(): Flow<List<ExerciseCategory>> =
        dao.observeCategories().map { list -> list.map { it.toDomain() } }
}