package com.ganggym.bamagang.workout.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
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

    @Query("""
        SELECT DISTINCT e.* FROM exercises e
        LEFT JOIN exercise_muscles em ON em.exerciseId = e.id
        LEFT JOIN exercise_equipment ee ON ee.exerciseId = e.id
        WHERE e.deletedAt IS NULL
          AND (:ignoreMuscles OR em.muscleId IN (:muscleIds))
          AND (:ignoreEquipment OR ee.equipmentId IN (:equipmentIds))
          AND (:query = '' OR e.name LIKE '%' || :query || '%')
        ORDER BY e.name
    """)
    fun filterRaw(
        muscleIds: List<String>,
        ignoreMuscles: Boolean,
        equipmentIds: List<String>,
        ignoreEquipment: Boolean,
        query: String,
    ): Flow<List<ExerciseEntity>>

    @Upsert
    suspend fun upsert(exercise: ExerciseEntity)

    @Upsert
    suspend fun upsertMuscles(refs: List<ExerciseMuscleCrossRef>)

    @Upsert
    suspend fun upsertEquipment(refs: List<ExerciseEquipmentCrossRef>)

    @Query("DELETE FROM exercise_muscles WHERE exerciseId = :id")
    suspend fun clearMuscles(id: String)

    @Query("DELETE FROM exercise_equipment WHERE exerciseId = :id")
    suspend fun clearEquipment(id: String)

    @Transaction
    suspend fun upsertFull(
        exercise: ExerciseEntity,
        muscles: List<ExerciseMuscleCrossRef>,
        equipment: List<ExerciseEquipmentCrossRef>,
    ) {
        upsert(exercise)
        clearMuscles(exercise.id)
        clearEquipment(exercise.id)
        upsertMuscles(muscles)
        upsertEquipment(equipment)
    }
}
