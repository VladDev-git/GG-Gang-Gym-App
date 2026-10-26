package com.ganggym.bamagang.workout.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profiles")
data class UserProfileEntity(
    @PrimaryKey val id: String,
    val displayName: String,
    val username: String?,
    val email: String?,
    val photoUrl: String?,
    val gender: String,
    val dateOfBirth: String?,    // ISO: "1995-03-17"
    val heightCm: Double?,
    val bodyWeightKg: Double?,
    val createdAt: Long,
    val updatedAt: Long,
    val deletedAt: Long?,
)