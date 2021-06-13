package com.smp.effectiveflow.airquality.di

import com.smp.effectiveflow.airquality.data.AirQualityDataSource
import com.smp.effectiveflow.airquality.data.AirQualityRepositoryImpl
import com.smp.effectiveflow.airquality.data.AirQualitySensorDataSource
import com.smp.effectiveflow.airquality.domain.AirQualityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AirQualityModule {
    @Binds
    abstract fun bindAirQualitySensorDataSource(
        airQualitySensorDataSource: AirQualitySensorDataSource
    ): AirQualityDataSource

    @Binds
    @Singleton
    abstract fun bindAirQualityRepository(
        airQualityRepository: AirQualityRepositoryImpl
    ): AirQualityRepository
}