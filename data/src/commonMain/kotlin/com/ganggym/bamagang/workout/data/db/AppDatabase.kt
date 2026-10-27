package com.ganggym.bamagang.workout.data.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.ganggym.bamagang.workout.data.db.dao.CatalogDao
import com.ganggym.bamagang.workout.data.db.dao.ExerciseDao
import com.ganggym.bamagang.workout.data.db.dao.ProfileDao
import com.ganggym.bamagang.workout.data.db.dao.RoutineDao
import com.ganggym.bamagang.workout.data.db.dao.SessionDao
import com.ganggym.bamagang.workout.data.db.dao.WorkoutDao
import com.ganggym.bamagang.workout.data.db.entity.EquipmentEntity
import com.ganggym.bamagang.workout.data.db.entity.ExerciseCategoryCrossRef
import com.ganggym.bamagang.workout.data.db.entity.ExerciseCategoryEntity
import com.ganggym.bamagang.workout.data.db.entity.ExerciseEntity
import com.ganggym.bamagang.workout.data.db.entity.ExerciseEquipmentCrossRef
import com.ganggym.bamagang.workout.data.db.entity.ExerciseMuscleCrossRef
import com.ganggym.bamagang.workout.data.db.entity.ExerciseResultEntity
import com.ganggym.bamagang.workout.data.db.entity.MuscleGroupEntity
import com.ganggym.bamagang.workout.data.db.entity.RoutineEntity
import com.ganggym.bamagang.workout.data.db.entity.UserProfileEntity
import com.ganggym.bamagang.workout.data.db.entity.WorkoutEntity
import com.ganggym.bamagang.workout.data.db.entity.WorkoutSessionEntity

@Database(
    entities = [
        WorkoutEntity::class,
        ExerciseEntity::class,
        ExerciseMuscleCrossRef::class,
        ExerciseEquipmentCrossRef::class,
        ExerciseCategoryCrossRef::class,
        RoutineEntity::class,
        WorkoutSessionEntity::class,
        ExerciseResultEntity::class,
        EquipmentEntity::class,
        MuscleGroupEntity::class,
        ExerciseCategoryEntity::class,
        UserProfileEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun routineDao(): RoutineDao
    abstract fun sessionDao(): SessionDao
    abstract fun catalogDao(): CatalogDao
    abstract fun profileDao(): ProfileDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
