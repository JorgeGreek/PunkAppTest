package com.example.punkapitestapp.ui.main

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.domain.BeersItem
import com.example.punkapitestapp.ui.common.ScopedViewModel
import com.example.punkapitestapp.ui.paging.ResultPagingSource
import com.example.usecase.GetBeers
import com.example.usecase.GetBeersByName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val getBeers: GetBeers,
    private val getBeersByName: GetBeersByName
) : ScopedViewModel() {


    fun apiData(searchName: String): Flow<PagingData<BeersItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 12,
                enablePlaceholders = false//,
            ),
            pagingSourceFactory = { ResultPagingSource(getBeers, getBeersByName, searchName) }
        ).flow
    }


}