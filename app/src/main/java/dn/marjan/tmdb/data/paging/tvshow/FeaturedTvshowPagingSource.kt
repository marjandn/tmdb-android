package dn.marjan.tmdb.data.paging.tvshow

import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.data.datasources.remote.services.TvshowRemoteDataSource
import dn.marjan.tmdb.data.model.TvshowResponse
import dn.marjan.tmdb.data.paging.BasePagingSource
import dn.marjan.tmdb.domain.entity.Tvshow
import javax.inject.Inject

class FeaturedTvshowPagingSource @Inject constructor(private val tvshowRemoteDataSource: TvshowRemoteDataSource) :
    BasePagingSource<Tvshow>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Tvshow> {
        val position = params.key ?: 1

        return try {
            val response: TvshowResponse =
                tvshowRemoteDataSource.getFeatureTvshows(PagingParam(position))
            val tvshows: List<Tvshow> = response.toEntity()

            LoadResult.Page(
                data = tvshows,
                prevKey = getPrevKey(position),
                nextKey = getNextKey(position, tvshows)
            )
        } catch (error: Exception) {
            LoadResult.Error(error)
        }
    }
}