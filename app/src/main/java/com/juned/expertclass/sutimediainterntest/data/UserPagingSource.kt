package com.juned.expertclass.sutimediainterntest.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.juned.expertclass.sutimediainterntest.data.remote.ApiService
import com.juned.expertclass.sutimediainterntest.data.remote.UserDataResponse

class UserPagingSource(private val apiService: ApiService) : PagingSource<Int, UserDataResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDataResponse> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getUser(params.loadSize, position, 2).body()!!.data
            LoadResult.Page(
                data = responseData,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserDataResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

}