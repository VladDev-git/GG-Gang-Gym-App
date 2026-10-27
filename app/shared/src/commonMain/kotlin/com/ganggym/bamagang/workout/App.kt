package com.ganggym.bamagang.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ganggym.bamagang.workout.domain.repository.WorkoutRepository
import org.koin.compose.koinInject

@Composable
fun App() {
    val repo = koinInject<WorkoutRepository>()
    val workouts by repo.observeAll().collectAsState(emptyList())

    MaterialTheme {
        Text("Тренувань у базі: ${workouts.size}")
    }
}
