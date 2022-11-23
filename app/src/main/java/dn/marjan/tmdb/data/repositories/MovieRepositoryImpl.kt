package dn.marjan.tmdb.data.repositories

import android.util.Log
import androidx.paging.*
import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.app.base.error.ServerException
import dn.marjan.tmdb.data.paging.movies.PopularMoviePagingSource
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.data.datasources.remote.parameters.SearchParam
import dn.marjan.tmdb.data.datasources.remote.services.MovieRemoteDataSource
import dn.marjan.tmdb.data.model.MovieResponse
import dn.marjan.tmdb.data.paging.movies.FeaturedMoviePagingSource
import dn.marjan.tmdb.data.paging.movies.SearchMoviePagingSource
import dn.marjan.tmdb.domain.entity.Movie
import dn.marjan.tmdb.domain.repository.MovieRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val popularMoviePagingSource: PopularMoviePagingSource,
    private val featuredMoviePagingSource: FeaturedMoviePagingSource,
    private val pageConfig: PagingConfig
) : MovieRepository {

    override fun getPopularMovies(): Flow<PagingData<Movie>> = Pager(pageConfig) {
        popularMoviePagingSource
    }.flow

    override suspend fun getFirstTenPopularMovies(): DataState<List<Movie>> {
        return try {
            val response: MovieResponse = movieRemoteDataSource.getPopularMovies(PagingParam(1))

            DataState.DataSuccessState(response.toEntity().take(10))
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }
    }

    override suspend fun getFirstTenUpcomingMovies(): DataState<List<Movie>> {
        return try {
            val response: MovieResponse = movieRemoteDataSource.getUpComingMovies(PagingParam(1))

            DataState.DataSuccessState(response.toEntity().take(10))
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }
    }

    override suspend fun getFirstTenFeaturedMovies(): DataState<List<Movie>> {
        return try {
            val response: MovieResponse = movieRemoteDataSource.getFeatureMoves(PagingParam(1))

            DataState.DataSuccessState(response.toEntity().take(10))
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }
    }


    override fun getFeatureMoves(): Flow<PagingData<Movie>> = Pager(pageConfig) {
        featuredMoviePagingSource
    }.flow

    override fun searchMovie(query: String): Flow<PagingData<Movie>> {
        return Pager(pageConfig) {
            SearchMoviePagingSource(movieRemoteDataSource, query)
        }.flow.catch {
            println("sth went wrong")
        }
    }
}