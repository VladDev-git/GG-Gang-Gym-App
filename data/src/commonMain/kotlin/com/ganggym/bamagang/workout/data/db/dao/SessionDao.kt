package com.ganggym.bamagang.workout.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.ganggym.bamagang.workout.data.db.entity.ExerciseResultEntity
import com.ganggym.bamagang.workout.data.db.entity.WorkoutSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {

    @Query("SELECT * FROM workout_sessions ORDER BY startedAt DESC")
    fun observeAll(): Flow<List<WorkoutSessionEntity>>

    @Query("SELECT * FROM workout_sessions WHERE completedAt IS NULL ORDER BY startedAt DESC LIMIT 1")
    fun observeActive(): Flow<WorkoutSessionEntity?>

    @Query("SELECT * FROM exercise_results WHERE sessionId = :sessionId ORDER BY completedAt")
    suspend fun resultsOf(sessionId: String): List<ExerciseResultEntity>

    @Query("""
        SELECT * FROM exercise_results
        WHERE exerciseId = :exerciseId AND type = 'WORKING'
        ORDER BY completedAt DESC LIMIT :limit
    """)
    fun observeHistory(exerciseId: String, limit: Int): Flow<List<ExerciseResultEntity>>

    @Upsert
    suspend fun upsertSession(session: WorkoutSessionEntity)

    @Upsert
    suspend fun upsertResults(results: List<ExerciseResultEntity>)
}