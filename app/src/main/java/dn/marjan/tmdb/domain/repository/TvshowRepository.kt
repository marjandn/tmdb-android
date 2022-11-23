package dn.marjan.tmdb.domain.repository

import androidx.paging.PagingData
import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.data.datasources.remote.parameters.SearchParam
import dn.marjan.tmdb.domain.entity.Tvshow
import kotlinx.coroutines.flow.Flow

interface TvshowRepository {

    fun getPopularTvshows(): Flow<PagingData<Tvshow>>

    fun getFeaturedTvshows(): Flow<PagingData<Tvshow>>

    suspend fun getFirstTenPopularTvshow(): DataState<List<Tvshow>>

    suspend fun getFirstTenFeaturedTvshow(): DataState<List<Tvshow>>

    fun searchTvshows(query: String): Flow<PagingData<Tvshow>>
}