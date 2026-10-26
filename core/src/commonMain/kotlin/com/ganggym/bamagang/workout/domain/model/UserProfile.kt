package com.ganggym.bamagang.workout.domain.model

import com.ganggym.bamagang.workout.domain.util.UserId
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

data class UserProfile(
    val id: UserId,
    val displayName: String,
    val username: String? = null,
    val email: String? = null,
    val photoUrl: String? = null,
    val gender: Gender = Gender.NOT_SPECIFIED,
    val dateOfBirth: LocalDate? = null,
    val heightCm: Double? = null,
    val bodyWeightKg: Double? = null,   // поточна вага
    val audit: AuditInfo,
)

fun UserProfile.ageAt(today: LocalDate): Int? =
    dateOfBirth?.let { birth ->
        var age = today.year - birth.year
        if (today < birth.plus(age, DateTimeUnit.YEAR)) age--
        age
    }
