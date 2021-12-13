package com.example.punkapitestapp.ui.detail.di

import androidx.lifecycle.SavedStateHandle
import com.example.data.repository.PunkRepository
import com.example.punkapitestapp.ui.detail.DetailActivity
import com.example.usecase.GetBeersById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import java.lang.IllegalStateException
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class DetailActivityModule {

    @Provides
    @Named("characterId")
    fun beerIdProvider(stateHandle: SavedStateHandle): Int =
        stateHandle.get<Int>(DetailActivity.BEER_ID)
            ?: throw IllegalStateException("beer id not found in the state handle")

    @ViewModelScoped
    @Provides
    fun provideGetDetails(punkRepository: PunkRepository): GetBeersById = GetBeersById(punkRepository)



}