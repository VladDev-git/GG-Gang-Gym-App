package com.ganggym.bamagang.workout.domain.model

enum class TrackingType(
    val usesLoad: Boolean,
    val usesReps: Boolean,
    val usesDuration: Boolean,
    val usesDistance: Boolean,
) {
    WEIGHT_AND_REPS(true, true, false, false),    // жим лежачи
    REPS_ONLY(false, true, false, false),         // підтягування
    DURATION(false, false, true, false),          // планка
    WEIGHT_AND_DURATION(true, false, true, false),// фермерська хода
    DISTANCE_AND_DURATION(false, false, true, true), // біг, гребля
}
