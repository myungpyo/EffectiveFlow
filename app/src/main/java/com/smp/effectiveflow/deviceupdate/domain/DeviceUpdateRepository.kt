package com.smp.effectiveflow.deviceupdate.domain

import kotlinx.coroutines.flow.Flow

interface DeviceUpdateRepository {
    fun downloadRecentBinary(): Flow<UpdateDownloadEvent>
}