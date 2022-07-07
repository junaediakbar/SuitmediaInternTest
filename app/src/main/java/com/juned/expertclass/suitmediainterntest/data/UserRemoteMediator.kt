package com.juned.expertclass.suitmediainterntest.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.juned.expertclass.suitmediainterntest.data.local.RemoteKeys
import com.juned.expertclass.suitmediainterntest.data.local.UserDatabase
import com.juned.expertclass.suitmediainterntest.data.remote.ApiService
import com.juned.expertclass.suitmediainterntest.data.remote.UserDataResponse
import com.juned.expertclass.suitmediainterntest.misc.wrapEspressoIdlingResource


@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(
    private val database: UserDatabase,
    private val apiService: ApiService,
) : RemoteMediator<Int, UserDataResponse>() {
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserDataResponse>
    ): MediatorResult {
        wrapEspressoIdlingResource {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKey?.nextKey?.minus(1) ?: INITIAL_PAGE
                }

                LoadType.PREPEND -> {
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    remoteKey?.prevKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
                }

                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    remoteKey?.nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
                }
            }

            try {
                val response = apiService
                    .getUser( state.config.pageSize, page)
                    .data

                val endOfPagination = response.isEmpty()

                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        database.remoteKeysDao().deleteRemoteKeys()
                        database.userDao().deleteAll()
                    }

                    val keys = response.map {
                        RemoteKeys(
                            id = it.email,
                            prevKey = if (page == 1) null else page - 1,
                            nextKey = if (endOfPagination) null else page + 1
                        )
                    }

                    database.remoteKeysDao().insertAll(keys)
                    database.userDao().insertUser(response)
                }

                return MediatorResult.Success(endOfPaginationReached = endOfPagination)
            } catch (ex: Exception) {
                return MediatorResult.Error(ex)
            }
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, UserDataResponse>) =
        state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { user ->
            database.remoteKeysDao().getRemoteKeysId(user.email)
        }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, UserDataResponse>) =
        state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { user ->
            database.remoteKeysDao().getRemoteKeysId(user.email)
        }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, UserDataResponse>) =
        state.anchorPosition?.let {
            state.closestItemToPosition(it)?.email?.let { id ->
                database.remoteKeysDao().getRemoteKeysId(id)
            }
        }

    companion object {
        const val INITIAL_PAGE = 1
    }

}