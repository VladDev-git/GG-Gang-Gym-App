package com.ganggym.bamagang.workout.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "seed_meta")
data class SeedMetaEntity(
    @PrimaryKey val file: String,   // "exercises/pull.json"
    val version: Int,
    val language: String,
)