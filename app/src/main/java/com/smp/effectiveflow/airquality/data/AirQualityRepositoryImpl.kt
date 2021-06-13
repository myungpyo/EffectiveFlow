package com.smp.effectiveflow.airquality.data

import com.smp.effectiveflow.airquality.domain.AirQuality
import com.smp.effectiveflow.airquality.domain.AirQualityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AirQualityRepositoryImpl @Inject constructor(
    private val airQualitySensorDataSource: AirQualitySensorDataSource
): AirQualityRepository {
    override fun observeAirQuality1(): Flow<AirQuality> {
        return airQualitySensorDataSource.observeAirQuality()
    }
    override fun observeAirQuality2(): Flow<AirQuality> {
        return airQualitySensorDataSource.observeAirQuality()
                .flowOn(Dispatchers.IO)
    }
}