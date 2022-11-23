package dn.marjan.tmdb.domain.repository

import androidx.paging.PagingData
import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.data.datasources.remote.parameters.SearchParam
import dn.marjan.tmdb.domain.entity.People
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
     fun getPopularPeople(): Flow<PagingData<People>>

    suspend fun getFirstTenPopularPeople(): DataState<List<People>>

    fun searchPeople(query: String): Flow<PagingData<People>>


}