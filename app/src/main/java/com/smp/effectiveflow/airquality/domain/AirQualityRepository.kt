package com.smp.effectiveflow.airquality.domain

import kotlinx.coroutines.flow.Flow

interface AirQualityRepository {
    fun observeAirQuality1(): Flow<AirQuality>
    fun observeAirQuality2(): Flow<AirQuality>
}