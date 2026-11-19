package com.ganggym.bamagang.workout.feature.exercises

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ganggym.bamagang.workout.domain.model.Exercise
import com.ganggym.bamagang.workout.domain.model.ExerciseFilter
import com.ganggym.bamagang.workout.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class ExercisesUiState(
    val exercises: List<Exercise> = emptyList(),
    val isLoading: Boolean = true,
)

class ExercisesViewModel(repository: ExerciseRepository) : ViewModel() {

    val state: StateFlow<ExercisesUiState> = repository.observe(ExerciseFilter())
        .map { ExercisesUiState(exercises = it, isLoading = false) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), ExercisesUiState())
}