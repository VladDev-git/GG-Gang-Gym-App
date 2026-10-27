package com.ganggym.bamagang.workout

import android.app.Application
import com.ganggym.bamagang.workout.di.initKoin
import org.koin.android.ext.koin.androidContext

class GangGymApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin { androidContext(this@GangGymApp) }
    }
}