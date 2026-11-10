package com.ganggym.bamagang.workout.data.seed

import com.ganggym.bamagang.workout.data.db.dao.CatalogDao
import com.ganggym.bamagang.workout.data.db.dao.ExerciseDao
import com.ganggym.bamagang.workout.data.db.entity.EquipmentEntity
import com.ganggym.bamagang.workout.data.db.entity.ExerciseCategoryEntity
import com.ganggym.bamagang.workout.data.db.entity.MuscleGroupEntity
import com.ganggym.bamagang.workout.data.db.entity.SeedMetaEntity
import com.ganggym.bamagang.workout.data.mapper.dbJson
import com.ganggym.bamagang.workout.data.mapper.toEntity
import com.ganggym.bamagang.workout.domain.model.AuditInfo
import com.ganggym.bamagang.workout.domain.model.DifficultyLevel
import com.ganggym.bamagang.workout.domain.model.Exercise
import com.ganggym.bamagang.workout.domain.model.TrackingType
import com.ganggym.bamagang.workout.domain.util.EquipmentId
import com.ganggym.bamagang.workout.domain.util.ExerciseCategoryId
import com.ganggym.bamagang.workout.domain.util.ExerciseId
import com.ganggym.bamagang.workout.domain.util.MuscleGroupId
import kotlin.time.Clock
import kotlin.time.Instant

fun interface SeedSource {
    suspend fun read(path: String): String
}

class CatalogSeeder internal constructor(
    private val source: SeedSource,
    private val catalogDao: CatalogDao,
    private val exerciseDao: ExerciseDao,
    private val clock: Clock,
) {
    suspend fun seed(language: String) {
        val lang = if (language == "uk") "uk" else "en"

        seedFile<MuscleDto>("muscles.json", lang) { items ->
            catalogDao.upsertMuscleGroups(items.mapIndexed { i, it ->
                MuscleGroupEntity(it.id, it.name.pick(lang), it.parentId, icon = null, sortOrder = i)
            })
        }
        seedFile<CatalogItemDto>("equipment.json", lang) { items ->
            catalogDao.upsertEquipment(items.mapIndexed { i, it ->
                EquipmentEntity(it.id, it.name.pick(lang), iconName = null, sortOrder = i)
            })
        }
        seedFile<CatalogItemDto>("categories.json", lang) { items ->
            catalogDao.upsertCategories(items.mapIndexed { i, it ->
                ExerciseCategoryEntity(it.id, it.name.pick(lang), iconName = null, sortOrder = i)
            })
        }
        EXERCISE_FILES.forEach { file ->
            seedFile<ExerciseDto>(file, lang) { items ->
                val now = clock.now()
                items.forEach { exerciseDao.upsertFull(it.toDomain(lang, now).toEntity()) }
            }
        }
    }

    private suspend inline fun <reified T> seedFile(file: String, lang: String, write: (List<T>) -> Unit) {
        val seed = dbJson.decodeFromString<SeedFile<T>>(source.read("files/seed/$file"))
        val meta = catalogDao.seedMeta(file)
        if (meta != null && meta.version >= seed.version && meta.language == lang) return
        write(seed.items)
        catalogDao.upsertSeedMeta(SeedMetaEntity(file, seed.version, lang))
    }

    private companion object {
        // Compose Resources не вміє читати вміст теки, тому список явний
        val EXERCISE_FILES = listOf("exercises/pull.json", "exercises/push.json", "exercises/core.json")
    }
}

private fun ExerciseDto.toDomain(lang: String, now: Instant) = Exercise(
    id = ExerciseId(id),
    name = name.pick(lang),
    tracking = TrackingType.valueOf(tracking),
    description = description.pick(lang),
    steps = steps.pick(lang),
    mistakes = mistakes.pick(lang),
    categoryIds = categories.map(::ExerciseCategoryId).toSet(),
    equipment = equipment.map { group -> group.map(::EquipmentId).toSet() },
    primaryMuscleIds = primaryMuscles.map(::MuscleGroupId).toSet(),
    secondaryMuscleIds = secondaryMuscles.map(::MuscleGroupId).toSet(),
    difficulty = DifficultyLevel.valueOf(difficulty),
    ownerId = null,             // вбудована вправа
    audit = AuditInfo(createdAt = now),
)
