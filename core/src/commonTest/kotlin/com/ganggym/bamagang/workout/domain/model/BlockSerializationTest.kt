package com.ganggym.bamagang.workout.domain.model

import com.ganggym.bamagang.workout.domain.util.ExerciseId
import com.ganggym.bamagang.workout.domain.util.WorkoutBlockId
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.seconds

class BlockSerializationTest {

    private val json = Json

    @Test
    fun blocksSurviveRoundTrip() {
        val blocks: List<WorkoutBlock> = listOf(
            ExerciseBlock(
                id = WorkoutBlockId("b1"),
                exerciseId = ExerciseId("bench"),
                sets = listOf(
                    SetTarget(SetType.WARMUP, reps = RepsTarget.Exact(12), load = LoadTarget.Kg(20.0)),
                    SetTarget(reps = RepsTarget.Range(8, 12), load = LoadTarget.PercentOf1RM(75.0)),
                    SetTarget(reps = RepsTarget.AMRAP, load = LoadTarget.Bodyweight),
                ),
                restAfter = 120.seconds,
            ),
            RestBlock(id = WorkoutBlockId("b2"), duration = 60.seconds),
            SupersetBlock(
                id = WorkoutBlockId("b3"),
                name = "Груди + спина",
                rounds = 3,
                items = listOf(
                    GroupItem(ExerciseId("chinups"), sets = listOf(SetTarget(reps = RepsTarget.Exact(10)))),
                ),
            ),
        )

        val encoded = json.encodeToString(blocks)
        assertEquals(blocks, json.decodeFromString<List<WorkoutBlock>>(encoded))
    }

    @Test
    fun sessionPlanSurvivesRoundTrip() {
        val plan = SessionPlan(
            workoutName = "Груди",
            blocks = listOf(RestBlock(id = WorkoutBlockId("b1"), duration = 90.seconds)),
            exerciseNames = mapOf(ExerciseId("bench") to "Жим лежачи"),
        )

        val encoded = json.encodeToString(plan)
        assertEquals(plan, json.decodeFromString<SessionPlan>(encoded))
    }
}
