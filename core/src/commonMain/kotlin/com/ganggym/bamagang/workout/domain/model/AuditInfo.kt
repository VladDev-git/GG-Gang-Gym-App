package com.ganggym.bamagang.workout.domain.model

import kotlin.time.Instant

data class AuditInfo(
    val createdAt: Instant,
    val updatedAt: Instant = createdAt,
    val deletedAt: Instant? = null,
) {
    val isDeleted: Boolean get() = deletedAt != null

    fun touched(now: Instant): AuditInfo = copy(updatedAt = now)

    fun deleted(now: Instant): AuditInfo =
        if (isDeleted) this else copy(updatedAt = now, deletedAt = now)
}
