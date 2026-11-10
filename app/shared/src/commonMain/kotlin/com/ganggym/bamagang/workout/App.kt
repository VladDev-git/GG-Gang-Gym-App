package com.ganggym.bamagang.workout

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.intl.Locale
import com.ganggym.bamagang.workout.data.seed.CatalogSeeder
import com.ganggym.bamagang.workout.domain.model.ExerciseFilter
import com.ganggym.bamagang.workout.domain.repository.ExerciseRepository
import org.koin.compose.koinInject

@Composable
fun App() {
    val seeder = koinInject<CatalogSeeder>()
    val exercises by koinInject<ExerciseRepository>()
        .observe(ExerciseFilter())
        .collectAsState(emptyList())
    val language = Locale.current.language   // androidx.compose.ui.text.intl.Locale

    LaunchedEffect(language) { seeder.seed(language) }

    MaterialTheme {
        Text("Вправ у базі: ${exercises.size}")
    }
}
