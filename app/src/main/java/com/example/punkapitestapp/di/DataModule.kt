package com.example.punkapitestapp.di

import com.example.data.repository.PunkRepository
import com.example.punkapitestapp.server.ThePunkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class  DataModule {

    @Singleton
    @Provides
    fun repositoryProvider(
        remoteDataSource: ThePunkDataSource
    ): PunkRepository = PunkRepository(remoteDataSource)

}