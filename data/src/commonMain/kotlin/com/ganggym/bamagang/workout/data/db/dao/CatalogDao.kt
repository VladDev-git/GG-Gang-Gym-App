package com.ganggym.bamagang.workout.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.ganggym.bamagang.workout.data.db.entity.EquipmentEntity
import com.ganggym.bamagang.workout.data.db.entity.ExerciseCategoryEntity
import com.ganggym.bamagang.workout.data.db.entity.MuscleGroupEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogDao {

    @Query("SELECT * FROM equipment ORDER BY sortOrder")
    fun observeEquipment(): Flow<List<EquipmentEntity>>

    @Query("SELECT * FROM muscle_groups ORDER BY sortOrder")
    fun observeMuscleGroups(): Flow<List<MuscleGroupEntity>>

    @Query("SELECT * FROM exercise_categories ORDER BY sortOrder")
    fun observeCategories(): Flow<List<ExerciseCategoryEntity>>

    @Query("SELECT * FROM muscle_groups WHERE parentId IS NULL ORDER BY sortOrder")
    fun observeTopMuscleGroups(): Flow<List<MuscleGroupEntity>>

    @Upsert
    suspend fun upsertEquipment(items: List<EquipmentEntity>)

    @Upsert
    suspend fun upsertMuscleGroups(items: List<MuscleGroupEntity>)

    @Upsert
    suspend fun upsertCategories(items: List<ExerciseCategoryEntity>)

    @Query("SELECT COUNT(*) FROM equipment")
    suspend fun equipmentCount(): Int
}