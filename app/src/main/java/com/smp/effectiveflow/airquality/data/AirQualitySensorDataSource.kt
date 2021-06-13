package com.smp.effectiveflow.airquality.data

import android.util.Log
import com.smp.effectiveflow.airquality.domain.AirQuality
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.math.abs
import kotlin.random.Random

class AirQualitySensorDataSource @Inject constructor(): AirQualityDataSource {

    override fun observeAirQuality(): Flow<AirQuality> {
        return flow {
            while (true) {
                delay(1000L)
                val airQuality = AirQuality(
                        pm2_5 = abs(Random.nextInt()) % 300,
                        pm10 = abs(Random.nextInt()) % 300
                )
                emit(airQuality)
                Log.d("AQSensorDataSource", "emit : $airQuality")
            }
        }
    }
}