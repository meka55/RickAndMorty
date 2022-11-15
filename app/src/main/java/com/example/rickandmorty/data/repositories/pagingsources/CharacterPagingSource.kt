package com.example.rickandmorty.data.repositories.pagingsources

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.network.apiservices.CharacterApi
import com.example.rickandmorty.models.character.CharacterModel
import okio.IOException
import retrofit2.HttpException

class CharacterPagingSource(
    private val characterApi: CharacterApi
) : PagingSource<Int, CharacterModel>() {

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, CharacterModel> {
        val position = params.key ?: 1
        return try {
            val response = characterApi.fetchCharacter(position)
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
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(anchorPosition = it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}