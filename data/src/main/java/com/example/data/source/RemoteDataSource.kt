package com.example.data.source

import com.example.domain.BeersItem

interface RemoteDataSource {
    suspend fun getBeers(page: Int): List<BeersItem>
    suspend fun getBeerDetails(id: Int): List<BeersItem>
    suspend fun getSearchBeer(name: String, page: Int): List<BeersItem>
}