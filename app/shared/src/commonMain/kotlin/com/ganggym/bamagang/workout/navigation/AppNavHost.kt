package com.ganggym.bamagang.workout.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ganggym.bamagang.workout.feature.exercises.ExercisesScreen
import com.ganggym.bamagang.workout.feature.workouts.WorkoutsScreen
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val current = backStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar {
                TopLevelTab.entries.forEach { tab ->
                    NavigationBarItem(
                        selected = current?.hasRoute(tab.destination::class) == true,
                        onClick = {
                            navController.navigate(tab.destination) {
                                popUpTo(navController.graph.findStartDestination().id) {saveState = true}
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {},
                        label = { Text(stringResource(tab.label)) },
                    )
                }
            }
        },
    ) { paddingValues ->
        NavHost(
            startDestination = ExercisesDestination,
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<ExercisesDestination> { ExercisesScreen() }
            composable<WorkoutsDestination> { WorkoutsScreen() }
        }
    }
}