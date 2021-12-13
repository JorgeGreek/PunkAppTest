package com.example.punkapitestapp.ui.main.di

import com.example.data.repository.PunkRepository
import com.example.usecase.GetBeers
import com.example.usecase.GetBeersByName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class MainActivityModule {

    @ViewModelScoped
    @Provides
    fun provideGetBeers(punkRepository: PunkRepository): GetBeers = GetBeers(punkRepository)

    @ViewModelScoped
    @Provides
    fun provideGetBeersByName(punkRepository: PunkRepository): GetBeersByName = GetBeersByName(punkRepository)

}