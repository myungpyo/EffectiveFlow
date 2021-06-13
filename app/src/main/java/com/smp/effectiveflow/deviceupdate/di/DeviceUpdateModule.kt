package com.smp.effectiveflow.deviceupdate.di

import com.smp.effectiveflow.deviceupdate.data.DeviceUpdateDataSource
import com.smp.effectiveflow.deviceupdate.data.DeviceUpdateRemoteDataSource
import com.smp.effectiveflow.deviceupdate.data.DeviceUpdateRepositoryImpl
import com.smp.effectiveflow.deviceupdate.domain.DeviceUpdateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DeviceUpdateModule {

    @Binds
    abstract fun bindDeviceUpdateRemoteDataSource(
        deviceUpdateRemoteDataSource: DeviceUpdateRemoteDataSource
    ): DeviceUpdateDataSource

    @Singleton
    @Binds
    abstract fun bindDeviceUpdateRepository(
        deviceUpdateRepositoryImpl: DeviceUpdateRepositoryImpl
    ): DeviceUpdateRepository
}