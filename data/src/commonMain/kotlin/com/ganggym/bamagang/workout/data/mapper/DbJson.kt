package com.ganggym.bamagang.workout.data.mapper

import com.ganggym.bamagang.workout.domain.model.AuditInfo
import kotlinx.serialization.json.Json
import kotlin.time.Instant

internal val dbJson = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
}

internal inline fun <reified T : Enum<T>> String.toEnumOr(default: T): T =
    enumValues<T>().firstOrNull { it.name == this } ?: default

internal fun Long.toInstant(): Instant = Instant.fromEpochMilliseconds(this)

internal fun auditOf(createdAt: Long, updatedAt: Long, deletedAt: Long?) = AuditInfo(
    createdAt = createdAt.toInstant(),
    updatedAt = updatedAt.toInstant(),
    deletedAt = deletedAt?.toInstant(),
)
