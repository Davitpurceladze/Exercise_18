package com.example.exercise18.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.exercise18.network.Network
import com.example.exercise18.network.Repository
import com.example.exercise18.response.User
import com.example.exercise18.service.UserService
import retrofit2.HttpException


class UsersPagingSource(private val backEnd: Repository ) : PagingSource<Int, User>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val currentPage = params.key ?: 1
            val response = backEnd.getUsers(currentPage)
            val data = response.body()!!.data
            val responseData = mutableListOf<User>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if(currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
        return null
    }
}