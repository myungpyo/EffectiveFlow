package com.smp.effectiveflow.deviceupdate.data

import com.smp.effectiveflow.deviceupdate.domain.DeviceUpdateRepository
import com.smp.effectiveflow.deviceupdate.domain.UpdateDownloadEvent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeviceUpdateRepositoryImpl@Inject constructor(
    private val deviceUpdateRemoteDataSource: DeviceUpdateRemoteDataSource
): DeviceUpdateRepository {
    override fun downloadRecentBinary(): Flow<UpdateDownloadEvent> {
        return deviceUpdateRemoteDataSource.downloadRecentBinary()
    }

}