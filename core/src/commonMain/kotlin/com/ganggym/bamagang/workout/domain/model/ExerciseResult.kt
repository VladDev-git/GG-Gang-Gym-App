package com.ganggym.bamagang.workout.domain.model

import com.ganggym.bamagang.workout.domain.util.ExerciseId
import com.ganggym.bamagang.workout.domain.util.ExerciseResultId
import com.ganggym.bamagang.workout.domain.util.WorkoutBlockId
import kotlin.time.Duration
import kotlin.time.Instant

data class ExerciseResult(
    val id: ExerciseResultId,
    val exerciseId: ExerciseId,
    val blockId: WorkoutBlockId? = null, // блок у знімку плану
    val setIndex: Int? = null,            // який саме сет з плану (0, 1, 2...)
    val itemIndex: Int? = null,           // номера елемента групи
    val round: Int? = null,               // раунд, якщо це суперсет
    val type: SetType = SetType.WORKING,
    val reps: Int? = null,
    val weightKg: Double? = null,
    val duration: Duration? = null,
    val distanceMeters: Double? = null,
    val rpe: Double? = null, // важкість після сету
    val completedAt: Instant,
    val note: String = "",
) {
    init {
        require(reps != null || duration != null || distanceMeters != null) {
            "Результат має містити хоч один вимір"
        }
    }
}
