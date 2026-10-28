package com.ganggym.bamagang.workout.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.ganggym.bamagang.workout.data.db.entity.ExerciseCategoryCrossRef
import com.ganggym.bamagang.workout.data.db.entity.ExerciseEntity
import com.ganggym.bamagang.workout.data.db.entity.ExerciseEquipmentCrossRef
import com.ganggym.bamagang.workout.data.db.entity.ExerciseMuscleCrossRef
import com.ganggym.bamagang.workout.data.db.entity.ExerciseWithRefs
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercises WHERE deletedAt IS NULL ORDER BY name")
    fun observeAll(): Flow<List<ExerciseEntity>>

    @Transaction
    @Query("SELECT * FROM exercises WHERE id = :id")
    suspend fun findWithRefs(id: String): ExerciseWithRefs?

    @Transaction
    @Query("""
        SELECT DISTINCT e.* FROM exercises e
        LEFT JOIN exercise_muscles em ON em.exerciseId = e.id
        LEFT JOIN exercise_equipment ee ON ee.exerciseId = e.id
        LEFT JOIN exercise_category_refs ec ON ec.exerciseId = e.id 
        WHERE e.deletedAt IS NULL
          AND (:ignoreMuscles
               OR em.muscleId IN (:muscleIds)
               OR em.muscleId IN (SELECT id FROM muscle_groups WHERE parentId IN (:muscleIds)))
          AND (:ignoreEquipment OR ee.equipmentId IN (:equipmentIds))
          AND (:ignoreCategories OR ec.categoryId IN (:categoryIds))
          AND (:query = '' OR e.name LIKE '%' || :query || '%')
        ORDER BY e.name
    """)
    fun filterRaw(
        muscleIds: List<String>,
        ignoreMuscles: Boolean,
        equipmentIds: List<String>,
        ignoreEquipment: Boolean,
        categoryIds: List<String>,
        ignoreCategories: Boolean,
        query: String,
    ): Flow<List<ExerciseWithRefs>>

    @Upsert
    suspend fun upsert(exercise: ExerciseEntity)

    @Upsert
    suspend fun upsertMuscles(refs: List<ExerciseMuscleCrossRef>)

    @Upsert
    suspend fun upsertEquipment(refs: List<ExerciseEquipmentCrossRef>)

    @Upsert
    suspend fun upsertCategories(refs: List<ExerciseCategoryCrossRef>)

    @Query("DELETE FROM exercise_muscles WHERE exerciseId = :id")
    suspend fun clearMuscles(id: String)

    @Query("DELETE FROM exercise_equipment WHERE exerciseId = :id")
    suspend fun clearEquipment(id: String)

    @Query("DELETE FROM exercise_category_refs WHERE exerciseId = :id")
    suspend fun clearCategories(id: String)

    @Transaction
    suspend fun upsertFull(data: ExerciseWithRefs) {
        upsert(data.exercise)
        clearMuscles(data.exercise.id)
        clearEquipment(data.exercise.id)
        clearCategories(data.exercise.id)
        upsertMuscles(data.muscles)
        upsertEquipment(data.equipment)
        upsertCategories(data.categories)
    }
}
