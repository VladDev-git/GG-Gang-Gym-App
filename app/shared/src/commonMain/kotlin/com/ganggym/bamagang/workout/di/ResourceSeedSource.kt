package com.ganggym.bamagang.workout.di

import com.ganggym.bamagang.workout.data.seed.SeedSource
import ganggym.app.shared.generated.resources.Res

internal class ResourceSeedSource : SeedSource {
    override suspend fun read(path: String): String = Res.readBytes(path).decodeToString()
}
