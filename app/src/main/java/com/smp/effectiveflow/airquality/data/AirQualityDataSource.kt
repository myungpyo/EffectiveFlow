package com.smp.effectiveflow.airquality.data

import com.smp.effectiveflow.airquality.domain.AirQuality
import kotlinx.coroutines.flow.Flow

interface AirQualityDataSource {
    fun observeAirQuality(): Flow<AirQuality>
}