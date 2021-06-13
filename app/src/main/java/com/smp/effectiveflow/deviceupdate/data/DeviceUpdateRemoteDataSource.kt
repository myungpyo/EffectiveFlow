package com.smp.effectiveflow.deviceupdate.data

import com.smp.effectiveflow.deviceupdate.domain.UpdateDownloadEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeviceUpdateRemoteDataSource @Inject constructor() : DeviceUpdateDataSource {
    override fun downloadRecentBinary(): Flow<UpdateDownloadEvent> {
        return flow {
            emit(UpdateDownloadEvent.Started)

            runCatching {
                (1..100).forEach {
                    delay(1000L)
                    emit(
                        UpdateDownloadEvent.InProgress(it)
                    )
                }
            }.onSuccess {
                emit(UpdateDownloadEvent.Complete("file:///mock"))
            }.onFailure {
                emit(UpdateDownloadEvent.CompleteWithError(it))
            }
        }
    }
}