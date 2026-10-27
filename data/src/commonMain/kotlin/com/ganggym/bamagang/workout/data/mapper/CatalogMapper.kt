package com.ganggym.bamagang.workout.data.mapper

import com.ganggym.bamagang.workout.data.db.entity.EquipmentEntity
import com.ganggym.bamagang.workout.data.db.entity.ExerciseCategoryEntity
import com.ganggym.bamagang.workout.data.db.entity.MuscleGroupEntity
import com.ganggym.bamagang.workout.domain.model.Equipment
import com.ganggym.bamagang.workout.domain.model.ExerciseCategory
import com.ganggym.bamagang.workout.domain.model.MuscleGroup
import com.ganggym.bamagang.workout.domain.util.EquipmentId
import com.ganggym.bamagang.workout.domain.util.ExerciseCategoryId
import com.ganggym.bamagang.workout.domain.util.MuscleGroupId

fun EquipmentEntity.toDomain() = Equipment(EquipmentId(id), name, iconName)
fun Equipment.toEntity() = EquipmentEntity(id.value, name, iconName)

fun MuscleGroupEntity.toDomain() = MuscleGroup(MuscleGroupId(id), name, parentId?.let(::MuscleGroupId), icon)
fun MuscleGroup.toEntity() = MuscleGroupEntity(id.value, name, parentId?.value, icon)

fun ExerciseCategoryEntity.toDomain() = ExerciseCategory(ExerciseCategoryId(id), name, iconName)
fun ExerciseCategory.toEntity() = ExerciseCategoryEntity(id.value, name, iconName)
