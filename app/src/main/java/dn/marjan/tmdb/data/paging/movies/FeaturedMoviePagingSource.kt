package dn.marjan.tmdb.data.paging.movies

import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.data.datasources.remote.services.MovieRemoteDataSource
import dn.marjan.tmdb.data.model.MovieResponse
import dn.marjan.tmdb.data.paging.BasePagingSource
import dn.marjan.tmdb.domain.entity.Movie
import javax.inject.Inject

class FeaturedMoviePagingSource @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : BasePagingSource<Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
       val position = params.key ?: 1

        return try {
            val response: MovieResponse = movieRemoteDataSource.getFeatureMoves(PagingParam(page = position))
            val movies: List<Movie> = response.toEntity()

            LoadResult.Page(
                data = movies,
                prevKey = getPrevKey(position),
                nextKey = getNextKey(position, movies)
            )


        } catch (error: Exception) {
            return LoadResult.Error(error)
        }
    }

}
