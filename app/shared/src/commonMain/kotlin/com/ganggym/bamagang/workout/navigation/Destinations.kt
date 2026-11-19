package com.ganggym.bamagang.workout.navigation

import ganggym.app.shared.generated.resources.Res
import ganggym.app.shared.generated.resources.tab_exercises
import ganggym.app.shared.generated.resources.tab_workouts
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource

@Serializable data object ExercisesDestination
@Serializable data object WorkoutsDestination

enum class TopLevelTab(val destination: Any, val label: StringResource) {
    EXERCISES(ExercisesDestination, Res.string.tab_exercises),
    WORKOUTS(WorkoutsDestination, Res.string.tab_workouts),
}
