package com.ganggym.bamagang.workout.feature.workouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ganggym.app.shared.generated.resources.Res
import ganggym.app.shared.generated.resources.workouts_placeholder
import org.jetbrains.compose.resources.stringResource

@Composable
fun WorkoutsScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(stringResource(Res.string.workouts_placeholder))
    }
}