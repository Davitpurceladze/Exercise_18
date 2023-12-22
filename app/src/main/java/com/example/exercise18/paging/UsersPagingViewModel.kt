package com.example.exercise18.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.exercise18.network.Repository

class UsersPagingViewModel: ViewModel() {

    val usersList = Pager(PagingConfig(1)) {
        UsersPagingSource(Repository())
    }.flow.cachedIn(viewModelScope)
}