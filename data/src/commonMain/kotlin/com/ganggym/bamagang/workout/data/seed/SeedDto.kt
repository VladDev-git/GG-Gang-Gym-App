package com.ganggym.bamagang.workout.data.seed

import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonTransformingSerializer
import kotlinx.serialization.json.jsonArray

@Serializable
internal data class SeedFile<T>(val version: Int, val items: List<T>)

@Serializable
internal data class LocalizedText(val uk: String, val en: String) {
    fun pick(lang: String) = if (lang == "uk") uk else en
}

@Serializable
internal data class LocalizedList(val uk: List<String>, val en: List<String>) {
    fun pick(lang: String) = if (lang == "uk") uk else en
}

@Serializable
internal data class CatalogItemDto(val id: String, val name: LocalizedText)

@Serializable
internal data class MuscleDto(val id: String, val name: LocalizedText, val parentId: String? = null)

@Serializable
internal data class ExerciseDto(
    val id: String,
    val name: LocalizedText,
    val tracking: String,
    val difficulty: String,
    val description: LocalizedText,
    val steps: LocalizedList,
    val mistakes: LocalizedList,
    val categories: List<String>,
    @Serializable(with = EquipmentGroupsSerializer::class)
    val equipment: List<List<String>>,
    val primaryMuscles: List<String>,
    val secondaryMuscles: List<String>,
)

// "high_bar" → ["high_bar"], ["bench","wall_bars"] лишається групою
internal object EquipmentGroupsSerializer :
    JsonTransformingSerializer<List<List<String>>>(ListSerializer(ListSerializer(String.serializer()))) {
    override fun transformDeserialize(element: JsonElement): JsonElement =
        JsonArray(element.jsonArray.map { if (it is JsonArray) it else JsonArray(listOf(it)) })
}

