package com.ganggym.bamagang.workout.domain.model

import com.ganggym.bamagang.workout.domain.util.ExerciseId
import kotlin.time.Duration

data class SupersetItem(
    val exerciseId: ExerciseId,
    val target: SetTarget,                   // ціль на один раунд
    val restAfter: Duration = Duration.ZERO, // пауза перед наступною вправою
)
