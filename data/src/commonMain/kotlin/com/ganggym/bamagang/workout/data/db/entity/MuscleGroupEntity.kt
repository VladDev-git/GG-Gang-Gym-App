package com.ganggym.bamagang.workout.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "muscle_groups")
data class MuscleGroupEntity(
    @PrimaryKey val id: String,
    val name: String,
    val parentId: String?,
    val icon: String?,
)
