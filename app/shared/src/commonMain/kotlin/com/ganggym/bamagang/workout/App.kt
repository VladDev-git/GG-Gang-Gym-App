package com.ganggym.bamagang.workout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ganggym.bamagang.workout.navigation.AppNavHost
import com.ganggym.bamagang.workout.ui.GangGymTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    val appViewModel = koinViewModel<AppViewModel>()
    val isReady by appViewModel.isReady.collectAsStateWithLifecycle()
    val language = Locale.current.language

    LaunchedEffect(language) { appViewModel.onLanguage(language) }

    GangGymTheme {
        if (isReady) {
            AppNavHost()
        } else {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}
