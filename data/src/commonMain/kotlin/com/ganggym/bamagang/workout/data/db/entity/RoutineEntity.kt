package com.ganggym.bamagang.workout.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routines")
data class RoutineEntity(
    @PrimaryKey val id: String,
    val ownerId: String,
    val name: String,
    val description: String,
    val blocks: String,          // JSON
    val icon: String?,
    val createdAt: Long,
    val updatedAt: Long,
    val deletedAt: Long?,
)