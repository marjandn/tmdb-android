package dn.marjan.tmdb.data.repositories

import dn.marjan.tmdb.app.base.datastate.DataState
import dn.marjan.tmdb.app.base.error.ServerException
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.data.datasources.remote.parameters.SearchParam
import dn.marjan.tmdb.data.datasources.remote.services.MovieRemoteDataSource
import dn.marjan.tmdb.data.model.MovieResponse
import dn.marjan.tmdb.domain.entity.Movie
import dn.marjan.tmdb.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieRemoteDataSource: MovieRemoteDataSource) :
    MovieRepository {

    override suspend fun getPopularMovies(pageParam: PagingParam): DataState<List<Movie>> {

        return try {
            val response: MovieResponse = movieRemoteDataSource.getPopularMovies(pageParam)

            DataState.DataSuccessState(response.toEntity())
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }

    }

    override suspend fun getUpComingMovies(pageParam: PagingParam): DataState<List<Movie>> {

        return try {
            val response: MovieResponse = movieRemoteDataSource.getUpComingMovies(pageParam)

            DataState.DataSuccessState(response.toEntity())
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }

    }

    override suspend fun getFeatureMoves(pageParam: PagingParam): DataState<List<Movie>> {

        return try {
            val response: MovieResponse = movieRemoteDataSource.getFeatureMoves(pageParam)

            DataState.DataSuccessState(response.toEntity())
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }

    }

    override suspend fun searchMovie(searchParam: SearchParam): DataState<List<Movie>> {

        return try {
            val response: MovieResponse = movieRemoteDataSource.searchMovie(searchParam)

            DataState.DataSuccessState(response.toEntity())
        } catch (error: ServerException) {
            DataState.DataFailedState(error.errorMessage)
        }

    }
}