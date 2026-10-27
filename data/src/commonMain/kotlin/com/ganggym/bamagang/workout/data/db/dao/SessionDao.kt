package com.ganggym.bamagang.workout.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.ganggym.bamagang.workout.data.db.entity.ExerciseResultEntity
import com.ganggym.bamagang.workout.data.db.entity.SessionWithResults
import com.ganggym.bamagang.workout.data.db.entity.WorkoutSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {

    @Transaction
    @Query("SELECT * FROM workout_sessions ORDER BY startedAt DESC")
    fun observeAll(): Flow<List<SessionWithResults>>

    @Transaction
    @Query("SELECT * FROM workout_sessions WHERE completedAt IS NULL ORDER BY startedAt DESC LIMIT 1")
    fun observeActive(): Flow<SessionWithResults?>

    @Query("""
        SELECT * FROM exercise_results
        WHERE exerciseId = :exerciseId AND type = 'WORKING'
        ORDER BY completedAt DESC LIMIT :limit
    """)
    fun observeHistory(exerciseId: String, limit: Int): Flow<List<ExerciseResultEntity>>

    @Transaction
    @Query("SELECT * FROM workout_sessions WHERE id = :id")
    suspend fun find(id: String): SessionWithResults?

    @Query("DELETE FROM exercise_results WHERE sessionId = :sessionId")
    suspend fun clearResults(sessionId: String)

    @Transaction
    suspend fun saveFull(session: WorkoutSessionEntity, results: List<ExerciseResultEntity>) {
        upsertSession(session)
        clearResults(session.id)
        upsertResults(results)
    }

    @Upsert
    suspend fun upsertSession(session: WorkoutSessionEntity)

    @Upsert
    suspend fun upsertResults(results: List<ExerciseResultEntity>)
}