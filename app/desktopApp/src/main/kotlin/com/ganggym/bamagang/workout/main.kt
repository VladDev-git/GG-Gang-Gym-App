package com.ganggym.bamagang.workout

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.ganggym.bamagang.workout.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "GangGym",
        ) {
            App()
        }
    }
}
