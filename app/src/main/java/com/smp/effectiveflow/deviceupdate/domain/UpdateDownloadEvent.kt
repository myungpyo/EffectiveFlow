package com.smp.effectiveflow.deviceupdate.domain

sealed class UpdateDownloadEvent {
    object Started: UpdateDownloadEvent()
    data class InProgress(
        val percentage: Int
    ): UpdateDownloadEvent()
    data class Complete(
        val storedPath: String
    ): UpdateDownloadEvent()
    data class CompleteWithError(
        val throwable: Throwable
    ): UpdateDownloadEvent()
}