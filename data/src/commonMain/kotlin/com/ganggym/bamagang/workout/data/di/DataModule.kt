package com.ganggym.bamagang.workout.data.di

import com.ganggym.bamagang.workout.data.db.AppDatabase
import com.ganggym.bamagang.workout.data.db.DatabaseBuilderFactory
import com.ganggym.bamagang.workout.data.db.buildAppDatabase
import com.ganggym.bamagang.workout.data.repository.RoomCatalogRepository
import com.ganggym.bamagang.workout.data.repository.RoomExerciseRepository
import com.ganggym.bamagang.workout.data.repository.RoomProfileRepository
import com.ganggym.bamagang.workout.data.repository.RoomRoutineRepository
import com.ganggym.bamagang.workout.data.repository.RoomSessionRepository
import com.ganggym.bamagang.workout.data.repository.RoomWorkoutRepository
import com.ganggym.bamagang.workout.domain.repository.CatalogRepository
import com.ganggym.bamagang.workout.domain.repository.ExerciseRepository
import com.ganggym.bamagang.workout.domain.repository.ProfileRepository
import com.ganggym.bamagang.workout.domain.repository.RoutineRepository
import com.ganggym.bamagang.workout.domain.repository.SessionRepository
import com.ganggym.bamagang.workout.domain.repository.WorkoutRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import kotlin.time.Clock

expect fun platformDataModule(): Module

val dataModule = module {
    single<Clock> { Clock.System }

    single { get<DatabaseBuilderFactory>().create().buildAppDatabase() }

    single { get<AppDatabase>().workoutDao() }
    single { get<AppDatabase>().routineDao() }
    single { get<AppDatabase>().exerciseDao() }
    single { get<AppDatabase>().sessionDao() }
    single { get<AppDatabase>().catalogDao() }
    single { get<AppDatabase>().profileDao() }

    singleOf(::RoomWorkoutRepository) bind WorkoutRepository::class
    singleOf(::RoomRoutineRepository) bind RoutineRepository::class
    singleOf(::RoomExerciseRepository) bind ExerciseRepository::class
    singleOf(::RoomSessionRepository) bind SessionRepository::class
    singleOf(::RoomCatalogRepository) bind CatalogRepository::class
    singleOf(::RoomProfileRepository) bind ProfileRepository::class
}
