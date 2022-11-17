package dn.marjan.tmdb.domain.repository

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.data.datasources.remote.parameters.SearchParam
import dn.marjan.tmdb.domain.entity.Tvshow

interface TvshowRepository {

    suspend fun getPopularTvshows(pageParam: PagingParam): DataState<List<Tvshow>>
    suspend fun getFeaturedTvshows(pageParam: PagingParam): DataState<List<Tvshow>>

    suspend fun searchTvshows(searchParam: SearchParam): DataState<List<Tvshow>>
}