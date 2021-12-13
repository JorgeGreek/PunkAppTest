package com.example.punkapitestapp.server

import com.example.data.source.RemoteDataSource
import com.example.domain.BeersItem
import javax.inject.Inject

class ThePunkDataSource @Inject constructor(private val api: ApiService) : RemoteDataSource {

    override suspend fun getBeers(page: Int): List<BeersItem> {
        val request = api.listBeers(page)
        if (request.isSuccessful) return request.body()!!.map { it.toDomainBeerItem() } else return emptyList()
    }

    override suspend fun getBeerDetails(id: Int): List<BeersItem> {
        val request = api.getBeerDetail(id)
        if (request.isSuccessful) return request.body()!!.map { it.toDomainBeerItem() } else return emptyList()
    }

    override suspend fun getSearchBeer(name: String, page: Int): List<BeersItem> {
        val request = api.getSearchBeersList(name, page)
        if (request.isSuccessful) return request.body()!!.map { it.toDomainBeerItem() } else return emptyList()
    }
}

