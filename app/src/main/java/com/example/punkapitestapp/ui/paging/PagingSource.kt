package com.example.punkapitestapp.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.BeersItem
import com.example.punkapitestapp.server.DEFAULT_PAGE_INDEX
import com.example.usecase.GetBeers
import com.example.usecase.GetBeersByName
import retrofit2.HttpException
import java.io.IOException

class ResultPagingSource(
    val getBeers: GetBeers,
    val getBeersByName: GetBeersByName,
    val searchName: String
) : PagingSource<Int, BeersItem>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, BeersItem> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        var response: List<BeersItem>
        if (searchName.length == 0) {
            response = getBeers.invoke(page)
        } else {
            response = getBeersByName.invoke(searchName, page)
        }
        return try {
            val results = response
            LoadResult.Page(
                data = results,
                prevKey = null,
                nextKey = if (results!!.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BeersItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(DEFAULT_PAGE_INDEX) ?: anchorPage?.nextKey?.minus(
                DEFAULT_PAGE_INDEX
            )
        }
    }

}
