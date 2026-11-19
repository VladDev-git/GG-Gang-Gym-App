package com.ganggym.bamagang.workout.feature.exercises

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ganggym.app.shared.generated.resources.Res
import ganggym.app.shared.generated.resources.exercises_count
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ExercisesScreen(viewModel: ExercisesViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ExercisesContent(state)
}

@Composable
private fun ExercisesContent(state: ExercisesUiState) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (state.isLoading) CircularProgressIndicator()
        else Text(stringResource(Res.string.exercises_count, state.exercises.size))
    }
}