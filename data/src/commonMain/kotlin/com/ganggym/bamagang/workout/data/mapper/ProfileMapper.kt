package com.ganggym.bamagang.workout.data.mapper

import com.ganggym.bamagang.workout.data.db.entity.UserProfileEntity
import com.ganggym.bamagang.workout.domain.model.Gender
import com.ganggym.bamagang.workout.domain.model.UserProfile
import com.ganggym.bamagang.workout.domain.util.UserId
import kotlinx.datetime.LocalDate

fun UserProfileEntity.toDomain() = UserProfile(
    id = UserId(id),
    displayName = displayName,
    username = username,
    email = email,
    photoUrl = photoUrl,
    gender = gender.toEnumOr(Gender.NOT_SPECIFIED),
    dateOfBirth = dateOfBirth?.let { runCatching { LocalDate.parse(it) }.getOrNull() },
    heightCm = heightCm,
    bodyWeightKg = bodyWeightKg,
    audit = auditOf(createdAt, updatedAt, deletedAt),
)

fun UserProfile.toEntity() = UserProfileEntity(
    id = id.value,
    displayName = displayName,
    username = username,
    email = email,
    photoUrl = photoUrl,
    gender = gender.name,
    dateOfBirth = dateOfBirth?.toString(),
    heightCm = heightCm,
    bodyWeightKg = bodyWeightKg,
    createdAt = audit.createdAt.toEpochMilliseconds(),
    updatedAt = audit.updatedAt.toEpochMilliseconds(),
    deletedAt = audit.deletedAt?.toEpochMilliseconds(),
)
