package com.ganggym.bamagang.workout.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.ganggym.bamagang.workout.data.db.entity.UserProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Query("SELECT * FROM user_profiles LIMIT 1")
    fun observeCurrent(): Flow<UserProfileEntity?>

    @Query("SELECT * FROM user_profiles WHERE id = :id")
    suspend fun find(id: String): UserProfileEntity?

    @Upsert
    suspend fun upsert(profile: UserProfileEntity)
}