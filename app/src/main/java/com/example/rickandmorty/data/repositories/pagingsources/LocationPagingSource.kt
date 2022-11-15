package com.example.rickandmorty.data.repositories.pagingsources

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.network.apiservices.LocationApi
import com.example.rickandmorty.models.location.LocationModel
import okio.IOException
import retrofit2.HttpException

class LocationPagingSource (
    private val locationApi: LocationApi
) : PagingSource<Int, LocationModel>() {

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, LocationModel>{
        val position = params.key ?: 1
        return try {
            val response = locationApi.fetchLocation(position)
            val nextPage = response.info.next
            val nextPagedNumber = if (nextPage == null) {
                null
            } else {
                Uri.parse(response.info.next).getQueryParameter("page")?.toInt()
            }

            PagingSource.LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = nextPagedNumber
            )
        } catch (exception: IOException) {
            return PagingSource.LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return PagingSource.LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LocationModel>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(anchorPosition = it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}