package com.example.rickandmorty.data.repositories.pagingsources

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.network.apiservices.CharacterApi
import com.example.rickandmorty.data.network.apiservices.EpisodeApi
import com.example.rickandmorty.models.character.CharacterModel
import com.example.rickandmorty.models.episode.EpisodeModel
import okio.IOException
import retrofit2.HttpException

class EpisodePagingSource(
    private val episodeApi: EpisodeApi
) : PagingSource<Int, EpisodeModel>() {

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, EpisodeModel> {
        val position = params.key ?: 1
        return try {
            val response = episodeApi.fetchEpisode(position)
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

    override fun getRefreshKey(state: PagingState<Int, EpisodeModel>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(anchorPosition = it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}