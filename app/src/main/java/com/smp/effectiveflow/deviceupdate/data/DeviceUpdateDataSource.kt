package com.smp.effectiveflow.deviceupdate.data

import com.smp.effectiveflow.deviceupdate.domain.UpdateDownloadEvent
import kotlinx.coroutines.flow.Flow

interface DeviceUpdateDataSource {
    fun downloadRecentBinary(): Flow<UpdateDownloadEvent>
}