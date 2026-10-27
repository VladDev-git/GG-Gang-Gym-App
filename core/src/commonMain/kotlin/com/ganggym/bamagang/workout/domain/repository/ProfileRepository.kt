package com.ganggym.bamagang.workout.domain.repository

import com.ganggym.bamagang.workout.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun observeCurrent(): Flow<UserProfile?>
    suspend fun save(profile: UserProfile)
}