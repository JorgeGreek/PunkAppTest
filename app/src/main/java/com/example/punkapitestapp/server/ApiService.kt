package com.example.punkapitestapp.server

import com.example.punkapitestapp.server.models.BeersItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("v2/beers")
    suspend fun listBeers(
        @Query("page") page: Int,
        @Query("per_page") elementsByPage: Int = VALUE_COUNT_PAGE
    ): Response<List<BeersItem>>


    @GET("v2/beers/{id}")
    suspend fun getBeerDetail(
        @Path("id") id: Int
    ): Response<List<BeersItem>>


    @GET("v2/beers")
    suspend fun getSearchBeersList(
        @Query("beer_name") beerName: String,
        @Query("page") page: Int,
        @Query("per_page") elementsByPage: Int = VALUE_COUNT_PAGE
    ): Response<List<BeersItem>>

}