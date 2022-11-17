package dn.marjan.tmdb.domain.repository

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.data.datasources.remote.parameters.SearchParam
import dn.marjan.tmdb.domain.entity.People

interface PeopleRepository {
    suspend fun getPopularPeople(pagingParam: PagingParam): DataState<List<People>>

    suspend fun searchPeople(searchParam: SearchParam): DataState<List<People>>


}