package com.ganggym.bamagang.workout.data.repository

import com.ganggym.bamagang.workout.data.db.dao.ProfileDao
import com.ganggym.bamagang.workout.data.mapper.toDomain
import com.ganggym.bamagang.workout.data.mapper.toEntity
import com.ganggym.bamagang.workout.domain.model.UserProfile
import com.ganggym.bamagang.workout.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class RoomProfileRepository(
    private val dao: ProfileDao,
) : ProfileRepository {
    override fun observeCurrent(): Flow<UserProfile?> =
        dao.observeCurrent().map { it?.toDomain() }

    override suspend fun save(profile: UserProfile) =
        dao.upsert(profile.toEntity())
}