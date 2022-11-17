package dn.marjan.tmdb.data.repositories

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.app.base.error.ServerException
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.data.datasources.remote.parameters.SearchParam
import dn.marjan.tmdb.data.datasources.remote.services.TvshowRemoteDataSource
import dn.marjan.tmdb.domain.entity.Tvshow
import dn.marjan.tmdb.domain.repository.TvshowRepository
import javax.inject.Inject

class TvshowRepositoryImpl @Inject constructor(private val tvshowRemoteDataSource: TvshowRemoteDataSource) :
    TvshowRepository {

    override suspend fun getPopularTvshows(pageParam: PagingParam): DataState<List<Tvshow>> {
        return try {
            val res = tvshowRemoteDataSource.getPopularTvshows(pageParam)
            DataState.DataSuccessState(data = res.toEntity())
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }
    }

    override suspend fun getFeaturedTvshows(pageParam: PagingParam): DataState<List<Tvshow>> {
        return try {
            val res = tvshowRemoteDataSource.getFeatureTvshows(pageParam)
            DataState.DataSuccessState(data = res.toEntity())
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }
    }

    override suspend fun searchTvshows(searchParam: SearchParam): DataState<List<Tvshow>> {
        return try {
            val res = tvshowRemoteDataSource.searchTvshow(searchParam)
            DataState.DataSuccessState(data = res.toEntity())
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }
    }
}