package com.ganggym.bamagang.workout.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.ganggym.bamagang.workout.data.db.entity.RoutineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDao {

    @Query("SELECT * FROM routines WHERE deletedAt IS NULL ORDER BY name")
    fun observeAll(): Flow<List<RoutineEntity>>

    @Query("SELECT * FROM routines WHERE id = :id")
    suspend fun find(id: String): RoutineEntity?

    @Upsert
    suspend fun upsert(routine: RoutineEntity)

    @Query("UPDATE routines SET deletedAt = :now, updatedAt = :now WHERE id = :id")
    suspend fun softDelete(id: String, now: Long)
}
