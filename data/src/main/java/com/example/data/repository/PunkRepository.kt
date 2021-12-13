package com.example.data.repository

import com.example.data.source.RemoteDataSource
import com.example.domain.BeersItem

class PunkRepository(
    private val remoteDataSource: RemoteDataSource
    ) {

    suspend fun suspendBeers(page: Int): List<BeersItem> {
        return remoteDataSource.getBeers(page)
    }

    suspend fun suspendDetails(id: Int): List<BeersItem> {
        return remoteDataSource.getBeerDetails(id)
    }

    suspend fun suspendSearchBeers(name: String, page: Int): List<BeersItem> {
        return remoteDataSource.getSearchBeer(name, page)
    }
}