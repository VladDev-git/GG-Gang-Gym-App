package com.ganggym.bamagang.workout.domain.repository

import com.ganggym.bamagang.workout.domain.model.Equipment
import com.ganggym.bamagang.workout.domain.model.ExerciseCategory
import com.ganggym.bamagang.workout.domain.model.MuscleGroup
import kotlinx.coroutines.flow.Flow

interface CatalogRepository {
    fun observeEquipment(): Flow<List<Equipment>>
    fun observeMuscleGroups(): Flow<List<MuscleGroup>>
    fun observeCategories(): Flow<List<ExerciseCategory>>
}